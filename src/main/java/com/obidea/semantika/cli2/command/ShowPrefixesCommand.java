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

import java.io.PrintStream;
import java.util.Map;

import com.obidea.semantika.cli2.runtime.ConsoleSession;
import com.obidea.semantika.util.StringUtils;

public class ShowPrefixesCommand extends Command
{
   private ConsoleSession mSession;

   public ShowPrefixesCommand(String command, ConsoleSession session)
   {
      mSession = session;
   }

   @Override
   public Object execute() throws Exception
   {
      return mSession.getPrefixes();
   }

   @Override
   public void printOutput(PrintStream out, Object output)
   {
      if (output instanceof Map<?, ?>) {
         @SuppressWarnings("unchecked")
         Map<String, String> map = (Map<String, String>) output;
         for (String key : map.keySet()) {
            String prefix = key;
            if (StringUtils.isEmpty(prefix)) {
               prefix = "(default)"; //
            }
            out.println(prefix + " = " + map.get(key));
         }
      }
      out.println();
   }
}
