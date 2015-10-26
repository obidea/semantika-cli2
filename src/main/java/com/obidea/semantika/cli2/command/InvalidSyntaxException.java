package com.obidea.semantika.cli2.command;

public class InvalidSyntaxException extends UnknownCommandException
{
   private static final long serialVersionUID = 629451L;

   private String mMessage;

   public InvalidSyntaxException(String message)
   {
      super();
      mMessage = message;
   }

   @Override
   public String getMessage()
   {
      return mMessage; //$NON-NLS-1$
   }
}
