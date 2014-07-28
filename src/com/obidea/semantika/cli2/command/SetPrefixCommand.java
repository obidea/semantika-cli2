package com.obidea.semantika.cli2.command;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.obidea.semantika.cli2.runtime.ConsoleSession;
import com.obidea.semantika.cli2.runtime.UnknownCommandException;

public class SetPrefixCommand extends Command
{
   private static final Pattern setPrefixCommand =
         Pattern.compile("set prefix \"(\\S+)\" with namespace \"(\\S+)\"", Pattern.CASE_INSENSITIVE); //$NON-NLS-1$

   private ConsoleSession mSession;

   private String mPrefix;
   private String mNamespace;

   public SetPrefixCommand(String command, ConsoleSession session) throws Exception
   {
      mSession = session;
      Matcher m = setPrefixCommand.matcher(command);
      if (m.matches()) {
         mPrefix = m.group(1).trim();
         mNamespace = m.group(2).trim();
      }
      else {
         throw new UnknownCommandException();
      }
   }

   @Override
   public Object execute() throws Exception
   {
      mSession.getPrefixMapper().put(mPrefix, mNamespace);
      return "Prefix added."; //$NON-NLS-1$
   }

   @Override
   public void printOutput(PrintStream out, Object output)
   {
      out.println(output.toString());
      out.println();
   }
}
