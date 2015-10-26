package com.obidea.semantika.cli2.command;

import java.io.PrintStream;

public abstract class Command
{
   protected static final String SELECT = "select"; //$NON-NLS-1$

   protected static final String SET_PREFIX = "set prefix"; //$NON-NLS-1$

   protected static final String SHOW_PREFIXES = "show prefixes"; //$NON-NLS-1$

   public abstract Object execute() throws Exception;

   public abstract void printOutput(PrintStream out, Object output);
}
