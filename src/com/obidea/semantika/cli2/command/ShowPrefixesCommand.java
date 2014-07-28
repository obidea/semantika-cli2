package com.obidea.semantika.cli2.command;

import java.io.PrintStream;
import java.util.Map;

import com.obidea.semantika.cli2.runtime.ConsoleSession;

public class ShowPrefixesCommand extends Command
{
   private ConsoleSession mSession;

   public ShowPrefixesCommand(String command, ConsoleSession session)
   {
      mSession = session;
   }

   @Override
   public Object execute() throws Exception
   {
      return mSession.getPrefixMapper();
   }

   @Override
   public void printOutput(PrintStream out, Object output)
   {
      if (output instanceof Map<?, ?>) {
         @SuppressWarnings("unchecked")
         Map<String, String> map = (Map<String, String>) output;
         for (String prefix : map.keySet()) {
            out.println(prefix + ": " + map.get(prefix));
         }
      }
      out.println();
   }
}
