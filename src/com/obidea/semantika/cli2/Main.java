package com.obidea.semantika.cli2;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import jline.Terminal;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.obidea.semantika.app.ApplicationFactory;
import com.obidea.semantika.app.ApplicationManager;
import com.obidea.semantika.cli2.runtime.Console;
import com.obidea.semantika.cli2.runtime.TerminalFactory;
import com.obidea.semantika.knowledgebase.IPrefixManager;
import com.obidea.semantika.queryanswer.IQueryEngine;

public class Main
{
   private static final String VERSION_NUMBER = "2.0"; //$NON-NLS-1$
   private static final String SEMANTIKA_CORE_VERSION_NUMBER = "1.7.1"; //$NON-NLS-1$

   @SuppressWarnings("unchecked")
   private static List<Logger> sLoggers = Collections.list(LogManager.getCurrentLoggers());
   static {
      sLoggers.add(LogManager.getRootLogger());
      for (Logger logger : sLoggers) {
         logger.setLevel(Level.OFF);
      }
   }

   public static void main(String[] args)
   {
      Main main = new Main();
      main.initialize(getConfig(args));
   }

   private static String getConfig(String[] args)
   {
      return args[0];
   }

   private void initialize(String config)
   {
      System.out.print("Initializing..."); //$NON-NLS-1$
      try {
         ApplicationManager manager = new ApplicationFactory().configure(config).createApplicationManager();
         openConsole(manager.createQueryEngine(),
               manager.getSettings().getPrefixManager(),
               manager.getSettings().getApplicationFactoryName());
      }
      catch (Exception e) {
         System.err.println(e.getMessage());
      }
   }

   private void openConsole(IQueryEngine engine, IPrefixManager pm, String name) throws Exception
   {
      final TerminalFactory terminalFactory = new TerminalFactory();
      Console console = createInteractiveConsole(name, engine, pm, terminalFactory.get());
      console.run();
   }

   private Console createInteractiveConsole(String name, IQueryEngine engine, IPrefixManager pm,
         Terminal terminal) throws IOException
   {
      showBanner();
      return new Console(name, engine, pm, terminal);
   }

   private void showBanner()
   {
      StringBuilder sb = new StringBuilder();
      sb.append("Semantika CLI2"); //$NON-NLS-1$
      sb.append(" ").append("(").append(VERSION_NUMBER); //$NON-NLS-1$ //$NON-NLS-2$
      sb.append(" ").append("SCR-").append(SEMANTIKA_CORE_VERSION_NUMBER); //$NON-NLS-1$ //$NON-NLS-2$
      sb.append(")\n"); //$NON-NLS-1$
      sb.append("Use \"Ctrl+D\" to exit.\n"); //$NON-NLS-1$
      System.out.print("\r"); //$NON-NLS-1$
      System.out.println(sb.toString());
   }
}
