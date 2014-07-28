package com.obidea.semantika.cli2.runtime;

import java.util.HashMap;
import java.util.Map;

import com.obidea.semantika.cli2.command.Command;
import com.obidea.semantika.cli2.command.CommandFactory;
import com.obidea.semantika.cli2.command.SelectCommand;
import com.obidea.semantika.cli2.command.SetPrefixCommand;
import com.obidea.semantika.cli2.command.ShowPrefixesCommand;
import com.obidea.semantika.knowledgebase.IPrefixManager;
import com.obidea.semantika.queryanswer.IQueryEngine;

public class ConsoleSession
{
   private IQueryEngine mQueryEngine;
   private Map<String, String> mPrefixMapper;

   public ConsoleSession(IQueryEngine engine, IPrefixManager prefixManager)
   {
      mQueryEngine = engine;
      mPrefixMapper = new HashMap<String, String>(prefixManager.getPrefixMapper());
   }

   public void start() throws Exception
   {
      mQueryEngine.start();
   }

   public Object execute(String command) throws Exception
   {
      Command cmd = CommandFactory.create(command);
      if (cmd instanceof SelectCommand) {
         return mQueryEngine.evaluate(cmd.arguments().get("string"));
      }
      else if (cmd instanceof SetPrefixCommand) {
         mPrefixMapper.put(cmd.arguments().get("prefix"), cmd.arguments().get("namespace"));
      }
      else if (cmd instanceof ShowPrefixesCommand) {
         return mPrefixMapper;
      }
      throw new Exception();
   }

   public void destroy() throws Exception
   {
      mQueryEngine.stop();
   }
}
