package com.obidea.semantika.cli2.command;

import java.io.PrintStream;
import java.util.Map;

import com.obidea.semantika.cli2.runtime.ConsoleSession;
import com.obidea.semantika.util.StringUtils;

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
      return mSession.getPrefixes();
   }

   @Override
   public void printOutput(PrintStream out, Object output)
   {
      if (output instanceof Map<?, ?>) {
         @SuppressWarnings("unchecked")
         Map<String, String> map = (Map<String, String>) output;
         for (String key : map.keySet()) {
            String prefix = key;
            if (StringUtils.isEmpty(prefix)) {
               prefix = "(default)"; //
            }
            out.println(prefix + " = " + map.get(key));
         }
      }
      out.println();
   }
}
