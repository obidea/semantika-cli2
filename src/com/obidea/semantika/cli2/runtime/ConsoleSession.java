package com.obidea.semantika.cli2.runtime;

import java.util.HashMap;
import java.util.Map;

import com.obidea.semantika.cli2.command.Command;
import com.obidea.semantika.cli2.command.CommandFactory;
import com.obidea.semantika.knowledgebase.IPrefixManager;
import com.obidea.semantika.queryanswer.IQueryEngine;

public class ConsoleSession
{
   private IQueryEngine mQueryEngine;
   private Map<String, String> mPrefixMapper;

   private Command mCommand;

   public ConsoleSession(IQueryEngine engine, IPrefixManager prefixManager)
   {
      mQueryEngine = engine;
      mPrefixMapper = new HashMap<String, String>(prefixManager.getPrefixMapper());
   }

   public void start() throws Exception
   {
      mQueryEngine.start();
   }

   public IQueryEngine getQueryEngine()
   {
      return mQueryEngine;
   }

   public Map<String, String> getPrefixMapper()
   {
      return mPrefixMapper;
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
