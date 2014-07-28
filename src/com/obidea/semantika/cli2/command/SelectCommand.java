package com.obidea.semantika.cli2.command;

import java.io.PrintStream;

import com.obidea.semantika.queryanswer.result.IQueryResult;

public class SelectCommand extends Command
{
   public SelectCommand(String selectQuery)
   {
      super(selectQuery);
   }

   @Override
   public void printOutput(PrintStream out, Object output)
   {
      if (output instanceof IQueryResult) {
         IQueryResult result = (IQueryResult) output;
         out.println(result.toString());
      }
      out.println();
   }
}
