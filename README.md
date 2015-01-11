Semantika CLI2
==============
The next generation of Semantika CLI that provides an interactive command line utility for SPARQL querying over existing database. The tool runs on Semantika API. The CLI tool will parse and process the input model and prepare you the query engine automatically.

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

User's Guide
------------

1. [Download and unzip the latest release](https://github.com/obidea/semantika-cli2/releases).
2. Create the [semantika-domain model](https://github.com/obidea/semantika-api/wiki/2.-Basic-RDB-RDF-Mapping).
3. Setup the [configuration file](https://github.com/obidea/semantika-api/wiki/1.-Semantika-Configuration).
4. Place the proper JDBC driver inside the `jdbc/` folder
5. Run `semantika <configuration-file>`

Need Help?
----------

Check [our Wikipage](https://github.com/obidea/semantika-api/wiki) for a brief introduction.
Need more help? Join [OBDA Semantika Forum](https://groups.google.com/forum/#!forum/obda-semantika).
