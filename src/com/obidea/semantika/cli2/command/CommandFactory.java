package com.obidea.semantika.cli2.command;

import com.obidea.semantika.cli2.runtime.ConsoleSession;
import com.obidea.semantika.cli2.runtime.UnknownCommandException;

public class CommandFactory
{
   public static Command create(String command, ConsoleSession session) throws UnknownCommandException
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
      throw new UnknownCommandException(); //$NON-NLS-1$
   }

   private static boolean startsWith(String command, String keyword)
   {
      return command.startsWith(keyword) || command.startsWith(keyword.toUpperCase());
   }
}
