/*
 * Copyright (c) 2013-2015 Obidea Technology
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.obidea.semantika.cli2.command;

import static java.lang.String.format;

import java.io.PrintStream;

import com.obidea.semantika.cli2.runtime.ConsoleSession;
import com.obidea.semantika.queryanswer.result.IQueryResult;

public class SelectCommand extends Command
{
   private String mCommand;
   private ConsoleSession mSession;

   public SelectCommand(String command, ConsoleSession session)
   {
      mCommand = command;
      mSession = session;
   }

   @Override
   public Object execute() throws Exception
   {
      String sparql = createSelectQuery();
      return mSession.getQueryEngine().evaluate(sparql);
   }

   private String createSelectQuery()
   {
      StringBuilder sb = new StringBuilder();
      for (String prefix : mSession.getPrefixes().keySet()) {
         sb.append(format("PREFIX %s: <%s>", prefix, mSession.getPrefixes().get(prefix)));
         sb.append("\n");
      }
      sb.append(mCommand);
      return sb.toString();
   }

   @Override
   public void printOutput(PrintStream out, Object output)
   {
      if (output instanceof IQueryResult) {
         IQueryResult result = (IQueryResult) output;
         while (result.next()) {
            out.println(result.getValueArray());
         }
      }
      out.println();
   }
}
