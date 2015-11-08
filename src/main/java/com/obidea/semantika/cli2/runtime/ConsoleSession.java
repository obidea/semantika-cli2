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
package com.obidea.semantika.cli2.runtime;

import java.util.Map;

import com.obidea.semantika.cli2.command.Command;
import com.obidea.semantika.cli2.command.CommandFactory;
import com.obidea.semantika.queryanswer.IQueryEngine;

public class ConsoleSession
{
   private IQueryEngine mQueryEngine;
   private Map<String, String> mPrefixes;

   private Command mCommand;

   public ConsoleSession(IQueryEngine engine, Map<String, String> prefixes)
   {
      mQueryEngine = engine;
      mPrefixes = prefixes;
   }

   public void start() throws Exception
   {
      mQueryEngine.start();
   }

   public IQueryEngine getQueryEngine()
   {
      return mQueryEngine;
   }

   public Map<String, String> getPrefixes()
   {
      return mPrefixes;
   }

   public Object execute(String command) throws Exception
   {
      mCommand = CommandFactory.create(command, this);
      return mCommand.execute();
   }

   public Command getCommand()
   {
      return mCommand;
   }

   public void destroy() throws Exception
   {
      mQueryEngine.stop();
   }
}
