package com.obidea.semantika.cli2.command;

public class CommandFactory
{
   public static Command create(String command) throws Exception
   {
      if (startsWith(command, Command.SELECT)) {
         return new SelectCommand(command);
      }
      else if (startsWith(command, Command.SET_PREFIX)) {
         return new SetPrefixCommand(command);
      }
      else if (startsWith(command, Command.SHOW_PREFIXES)) {
         return new ShowPrefixesCommand(command);
      }
      throw new Exception("Unknown command"); //$NON-NLS-1$
   }

   private static boolean startsWith(String command, String keyword)
   {
      return command.startsWith(keyword) || command.startsWith(keyword.toUpperCase());
   }
}
