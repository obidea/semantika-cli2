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
