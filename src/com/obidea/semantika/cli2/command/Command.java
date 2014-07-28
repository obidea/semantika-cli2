package com.obidea.semantika.cli2.command;

import java.util.HashMap;
import java.util.Map;

public abstract class Command
{
   protected static final String SELECT = "select"; //$NON-NLS-1$

   protected static final String SET_PREFIX = "set prefix"; //$NON-NLS-1$

   protected static final String SHOW_PREFIXES = "show prefixes"; //$NON-NLS-1$

   private Map<String, String> mArguments = new HashMap<String, String>();

   public Command(String command)
   {
      mArguments.put("string", command);
   }

   public Map<String, String> arguments()
   {
      return mArguments;
   }
}
