package com.obidea.semantika.cli2.command;

import com.obidea.semantika.cli2.runtime.ConsoleSession;

public class CommandFactory
{
   public static Command create(String command, ConsoleSession session) throws Exception
   {
      if (startsWith(command, Command.SELECT)) {
         return new SelectCommand(command, session);
      }
      else if (startsWith(command, Command.SET_PREFIX)) {
         return new SetPrefixCommand(command, session);
      }
      else if (startsWith(command, Command.SHOW_PREFIXES)) {
         return new ShowPrefixesCommand(command, session);
      }
      throw new Exception("Unknown command"); //$NON-NLS-1$
   }

   private static boolean startsWith(String command, String keyword)
   {
      return command.startsWith(keyword) || command.startsWith(keyword.toUpperCase());
   }
}
