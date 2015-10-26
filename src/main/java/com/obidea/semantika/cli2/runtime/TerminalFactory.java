package com.obidea.semantika.cli2.runtime;

import jline.NoInterruptUnixTerminal;
import jline.Terminal;

public class TerminalFactory
{
   private Terminal mTerminal;

   public synchronized Terminal get() throws Exception
   {
      if (mTerminal == null) {
         jline.TerminalFactory.registerFlavor(jline.TerminalFactory.Flavor.UNIX,
               NoInterruptUnixTerminal.class);
         mTerminal = jline.TerminalFactory.create();
      }
      return mTerminal;
   }

   public synchronized void destroy() throws Exception
   {
      if (mTerminal != null) {
         mTerminal.restore();
         mTerminal = null;
      }
   }
}
