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

   public Object execute(String command) throws Exception
   {
      mCommand = CommandFactory.create(command);
      if (mCommand instanceof SelectCommand) {
         return mQueryEngine.evaluate(mCommand.arguments().get("string"));
      }
      else if (mCommand instanceof SetPrefixCommand) {
         mPrefixMapper.put(mCommand.arguments().get("prefix"), mCommand.arguments().get("namespace"));
      }
      else if (mCommand instanceof ShowPrefixesCommand) {
         return mPrefixMapper;
      }
      throw new UnknownCommandException();
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
