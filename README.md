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

Installation
------------

- [Download and unzip the latest release](https://github.com/obidea/semantika-cli2/releases). The package has a directory structure:
```
root/
  +-jdbc/
  +-lib/
  +-model/
  LICENSE.txt
  NOTICE.txt
  application.cfg.xml
  semantika
  semantika-cli-2.0.jar
```
- Put your JDBC database driver to the `jdbc/` folder,
- Put your mapping model and ontology (optional) to the `model/` folder,
- Prepare the `application.cfg.xml` to configure the database setting and model resources.

**Running the application**. Make sure your database is running in the background and run the command:
```
$ ./semantika application.cfg.xml
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

Need Help?
----------

Check [our Wikipage](https://github.com/obidea/semantika-api/wiki) for a brief introduction.
Need more help? Join [OBDA Semantika Forum](https://groups.google.com/forum/#!forum/obda-semantika).
