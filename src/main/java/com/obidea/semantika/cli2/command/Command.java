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

public abstract class Command
{
   protected static final String SELECT = "select"; //$NON-NLS-1$

   protected static final String SET_PREFIX = "set prefix"; //$NON-NLS-1$

   protected static final String SHOW_PREFIXES = "show prefixes"; //$NON-NLS-1$

   public abstract Object execute() throws Exception;

   public abstract void printOutput(PrintStream out, Object output);
}
