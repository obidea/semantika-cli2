package com.obidea.semantika.cli2.command;

public class CommandFactory
{
   public static Command create(String command) throws Exception
   {
      if (command.startsWith(Command.SELECT)) {
         return new SelectCommand(command);
      }
      else if (command.startsWith(Command.SET_PREFIX)) {
         return new SetPrefixCommand(command);
      }
      else if (command.startsWith(Command.SHOW_PREFIXES)) {
         return new ShowPrefixesCommand(command);
      }
      throw new Exception("Unknown command"); //$NON-NLS-1$
   }
}
