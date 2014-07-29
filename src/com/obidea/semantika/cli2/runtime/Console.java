package com.obidea.semantika.cli2.runtime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import jline.Terminal;
import jline.console.ConsoleReader;
import jline.console.history.FileHistory;
import jline.console.history.PersistentHistory;

import com.obidea.semantika.knowledgebase.IPrefixManager;
import com.obidea.semantika.queryanswer.IQueryEngine;

public class Console
{
   private String mConsoleName;
   private InputStream mInputStream;

   private Thread mPipeThread;
   private Thread mRunningThread;

   private boolean mInterrupted;
   private volatile boolean mRunning;
   private volatile boolean mEof;

   private BlockingQueue<Integer> mQueue = new ArrayBlockingQueue<Integer>(1024);

   private ConsoleReader mConsoleReader;
   private ConsoleSession mConsoleSession;

   public Console(IQueryEngine engine, IPrefixManager pm, String name, InputStream in,
         Terminal terminal) throws IOException
   {
      mConsoleName = name;
      mInputStream = in;
      mConsoleReader = createConsoleReader(name, terminal);
      mConsoleSession = new ConsoleSession(engine, pm);
      mPipeThread = new Thread(new Pipe());
      mPipeThread.setDaemon(true);
   }

   private ConsoleReader createConsoleReader(String name, Terminal terminal) throws IOException
   {
      ConsoleReader reader = new ConsoleReader(name, new ConsoleInputStream(), System.out, terminal);
      reader.setHistoryEnabled(false); // we will insert history manually
      reader.setHistory(new FileHistory(getHistoryFile()));
      return reader;
   }

   protected File getHistoryFile()
   {
      String defaultHistoryPath = new File(System.getProperty("user.home"), ".semantika_history").toString();
      return new File(System.getProperty("semantika.history", defaultHistoryPath));
   }

   private String getPrompt()
   {
      return mConsoleName + "> ";
   }

   private String getAltPrompt()
   {
      return "> ";
   }

   public void run() throws Exception
   {
      boolean graceExit = false;
      try {
         mConsoleSession.start();
         mPipeThread.start();
         mRunningThread = Thread.currentThread();
         mRunning = true;
         while (mRunning) {
            try {
               String command = readCommand();
               if (command == null) {
                  break; // exit the loop if command is null
               }
               Object result = mConsoleSession.execute(command);
               if (result != null) {
                  mConsoleSession.getCommand().printOutput(System.out, result);
               }
            }
            catch (Exception e) {
               System.err.println(e.getMessage());
            }
         }
         graceExit = true;
      }
      finally {
         terminate(graceExit);
      }
   }

   public void terminate(boolean terminatedByUser) throws Exception
   {
      if (!mRunning) {
         return;
      }
      if (mConsoleReader.getHistory() instanceof PersistentHistory) {
         try {
            ((PersistentHistory) mConsoleReader.getHistory()).flush();
         }
         catch (IOException e) {
            System.err.println(e.getMessage());
         }
      }
      mRunning = false;
      mConsoleSession.destroy();
      mPipeThread.interrupt();
      if (terminatedByUser) {
         System.out.println("Terminated by user. Good bye."); //$NON-NLS-1$
      }
   }

   private String readCommand()
   {
      StringBuilder command = new StringBuilder();
      boolean loop = true;
      boolean first = true;
      while (loop) {
         try {
            checkInterrupt();
            String line = mConsoleReader.readLine(first ? getPrompt() : getAltPrompt());
            if (line == null) {
               return null; // line can be null due to Ctrl+D signal
            }
            if (line.length() == 0 && first) {
               continue; // skip the processing if the first input line is empty
            }
            
            if (endOfCommand(line)) {
               line = line.substring(0, line.length() - 2); // remove EOC marker
               loop = false;
            }
            else {
               loop = true;
               first = false;
            }
            command.append(line).append("\n");
            
            // Save to history if the line is not empty
            if (line.trim().length() > 0) {
               mConsoleReader.getHistory().add(line);
            }
         }
         catch (IOException e) {
            first = true; // back to initial prompt
            if (e.getMessage() != null) {
               System.err.println(e.getMessage());
            }
         }
      }
      return command.toString().trim();
   }

   private boolean endOfCommand(String line)
   {
      if (line.length() < 2) {
         return false;
      }
      return line.charAt(line.length() - 1) == line.charAt(line.length() - 2);
   }

   private void interrupt()
   {
      mInterrupted = true;
      mRunningThread.interrupt();
   }

   private void checkInterrupt() throws IOException
   {
      if (Thread.interrupted() || mInterrupted) {
         mInterrupted = false;
         throw new InterruptedIOException("Keyboard interruption");
      }
   }

   private class Pipe implements Runnable
   {
      @Override
      public void run()
      {
         try {
            while (mRunning) {
               try {
                  int c = mInputStream.read();
                  switch (c) {
                     case -1 :
                        return;
                     case 3 : // ASCII code for CTRL+C
                        System.err.println("^C");
                        mConsoleReader.getCursorBuffer().clear();
                        interrupt();
                        break;
                     case 4 : // ASCII code for CTRL+D
                        System.err.println("^D");
                        return;
                     default :
                        mQueue.put(c);
                  }
               }
               catch (Throwable t) {
                  return;
               }
            }
         }
         finally {
            mEof = true;
            try {
               mQueue.put(-1);
            }
            catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      }
   }

   private class ConsoleInputStream extends InputStream
   {
      @Override
      public int read() throws IOException
      {
         if (!mRunning) {
            return -1;
         }
         checkInterrupt();
         if (mEof && mQueue.isEmpty()) {
            return -1;
         }
         Integer i;
         try {
            i = mQueue.take();
         }
         catch (InterruptedException e) {
            throw new InterruptedIOException();
         }
         checkInterrupt();
         if (i == null) {
            return -1;
         }
         return i;
      }
   }
}
