package com.obidea.semantika.cli2.command;

public class UnknownCommandException extends Exception
{
   private static final long serialVersionUID = 629451L;

   public UnknownCommandException()
   {
      super();
   }

   @Override
   public String getMessage()
   {
      return "Unknown command."; //$NON-NLS-1$
   }
}
