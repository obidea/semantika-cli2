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
package com.obidea.semantika.cli2;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jline.Terminal;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.obidea.semantika.app.ApplicationFactory;
import com.obidea.semantika.app.ApplicationManager;
import com.obidea.semantika.cli2.runtime.Console;
import com.obidea.semantika.cli2.runtime.TerminalFactory;
import com.obidea.semantika.knowledgebase.IPrefixManager;
import com.obidea.semantika.queryanswer.IQueryEngine;

public class Main
{
   private static final String VERSION_NUMBER = "2.1"; //$NON-NLS-1$
   private static final String SEMANTIKA_CORE_VERSION_NUMBER = "1.8"; //$NON-NLS-1$

   @SuppressWarnings("unchecked")
   private static List<Logger> sLoggers = Collections.list(LogManager.getCurrentLoggers());
   static {
      sLoggers.add(LogManager.getRootLogger());
      for (Logger logger : sLoggers) {
         logger.setLevel(Level.OFF);
      }
   }

   public static void main(String[] args)
   {
      Main main = new Main();
      main.initialize(getConfig(args), debugMode(args));
   }

   private static String getConfig(String[] args)
   {
      return args[0];
   }

   private static boolean debugMode(String[] args)
   {
      if (args.length > 1) {
         if (args[1].equals("-debug")) {
            return true;
         }
      }
      return false;
   }

   private void initialize(String config, boolean isDebugMode)
   {
      System.out.print("Initializing..."); //$NON-NLS-1$
      
      if (isDebugMode) {
         for (Logger logger : sLoggers) {
            logger.setLevel(Level.DEBUG);
        }
      }
      try {
         ApplicationManager manager = new ApplicationFactory().configure(config).createApplicationManager();
         openConsole(manager.getApplicationName(),
               manager.createQueryEngine(),
               manager.getPrefixManager());
      }
      catch (Exception e) {
         System.err.println(e.getMessage());
      }
   }

   private void openConsole(String consoleName, IQueryEngine engine, IPrefixManager pm) throws Exception
   {
      TerminalFactory terminalFactory = new TerminalFactory();
      Map<String, String> prefixes = new HashMap<String, String>(pm.getPrefixMapper());
      Console console = createInteractiveConsole(consoleName, engine, prefixes, terminalFactory.get());
      console.run();
   }

   private Console createInteractiveConsole(String name, IQueryEngine engine, Map<String, String> prefixes,
         Terminal terminal) throws IOException
   {
      showBanner();
      return new Console(name, engine, prefixes, terminal);
   }

   private void showBanner()
   {
      StringBuilder sb = new StringBuilder();
      sb.append("Semantika CLI2"); //$NON-NLS-1$
      sb.append(" ").append("(").append(VERSION_NUMBER); //$NON-NLS-1$ //$NON-NLS-2$
      sb.append(" ").append("SCR-").append(SEMANTIKA_CORE_VERSION_NUMBER); //$NON-NLS-1$ //$NON-NLS-2$
      sb.append(")\n"); //$NON-NLS-1$
      sb.append("Use \"Ctrl+D\" to exit.\n"); //$NON-NLS-1$
      System.out.print("\r"); //$NON-NLS-1$
      System.out.println(sb.toString());
   }
}
