package com.obidea.semantika.cli2.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetPrefixCommand extends Command
{
   private static final Pattern setPrefixCommand =
         Pattern.compile("set prefix \"(\\S+)\" with namespace \"(\\S+)\"", Pattern.CASE_INSENSITIVE); //$NON-NLS-1$

   public SetPrefixCommand(String command) throws Exception
   {
      super(command);
      Matcher m = setPrefixCommand.matcher(command);
      if (m.matches()) {
         arguments().put("prefix", m.group(1).trim()); //$NON-NLS-1$
         arguments().put("namespace", m.group(2).trim()); //$NON-NLS-1$
      }
      else {
         throw new Exception("Unknown command"); //$NON-NLS-1$
      }
   }
}
