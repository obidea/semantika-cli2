package com.obidea.semantika.cli2.command;

import java.io.PrintStream;
import java.util.Map;

public class ShowPrefixesCommand extends Command
{
   public ShowPrefixesCommand(String command)
   {
      super(command);
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
