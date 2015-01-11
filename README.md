Semantika CLI2
==============
The next generation of Semantika CLI that provides an interactive command line utility for SPARQL querying over existing database. The tool runs using [Semantika API](https://github.com/obidea/semantika-core). It gives a service interface for querying your existing database in SPARQL language.

Latest news: [2.0 (build 17.1-rev1) is now available](https://github.com/obidea/semantika-cli2/releases/download/v2.0_17.1-rev1/semantika-cli2-2.0_17.1-rev1.zip) (January 11, 2015)

```
$ ./semantika application.cfg.xml
Semantika CLI2 (2.0 SCR-1.7.1)
Use "Ctrl+D" to exit.

prompt>
```

Add `-debug` option to run the CLI tool in debugging mode.

```
$ ./semantika application.cfg.xml -debug
```

Commands
--------

* SPARQL SELECT expression

```
prompt> select ?x ?name ?age
> where {
>   ?x a :Person;
>     :fullName ?name;
>     :age ?age .
> filter ( ?age > 20 ) }
```

* SHOW PREFIXES

```
prompt> show prefixes
```

* SET PREFIX

```
prompt> set prefix "smp" with namespace "http://example.org/simple#"
```

Insert a blank line by tapping the <ENTER> key twice to run the command.

Demo Application
----------------

1. [Download and unzip the latest release](https://github.com/obidea/semantika-cli2/releases). Note the directory structure.
```
root
  +-jdbc/
  +-lib/
  +-model/
  LICENSE.txt
  NOTICE.txt
  application.cfg.xml
  semantika
  semantika-cli-2.0.jar
```
2. [Download the EMPDB database for H2](https://github.com/obidea/semantika-api/releases/download/v1.1/h2-semantika_24-02-2014.zip). Unpack and run the database in your local computer.
3. Put the [H2 driver](https://github.com/obidea/semantika-api/blob/master/jdbc/h2-1.3.174.jar?raw=true) inside the `jdbc/` folder,
4. Put both [the mapping model](https://raw.githubusercontent.com/obidea/semantika-api/master/model/empdb.mod.xml) and [the ontology](https://raw.githubusercontent.com/obidea/semantika-api/master/model/empdb.owl) inside `model/` folder,
5. Edit the `application.cfg.xml` to configure the database setting and model resources. Code snippet:
```
<semantika-configuration>
   <application-factory name="empdb-app">
      <data-source>
         <property name="connection.url">jdbc:h2:tcp://localhost/empdb</property>
         <property name="connection.driver_class">org.h2.Driver</property>
         <property name="connection.username">sa</property>
         <property name="connection.password"></property>
      </data-source>
      <ontology-source resource="model/empdb.owl" />
      <mapping-source resource="model/empdb.mod.xml" />
   </application-factory>
</semantika-configuration>
```
6. Run `./semantika application.cfg.xml` in the command prompt.
7. Try some of the queries [here](https://github.com/obidea/semantika/tree/gh-pages/demo). Note: you can omit the lines with `PREFIX:` definition.

Need Help?
----------

Check [our Wikipage](https://github.com/obidea/semantika-api/wiki) for a brief introduction.
Need more help? Join [OBDA Semantika Forum](https://groups.google.com/forum/#!forum/obda-semantika).
