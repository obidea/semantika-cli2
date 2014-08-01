Semantika CLI2
==============
The next generation of Semantika CLI that provides an interactive command line utility for SPARQL querying over existing database. The tool runs on Semantika framework that uses *data-to-domain mappings* (see [the example](https://github.com/obidea/semantika-api/blob/master/model/empdb.mod.xml)). The CLI tool will parse and process the input model and prepare you the query engine automatically.

Latest news: [2.0 (build 17.1) is now available](https://github.com/obidea/semantika-cli2/releases/tag/v2.0_17.1) (July 31, 2014)

```
$ ./semantika application.cfg.xml
Semantika CLI2 (2.0 SCR-1.7.1)
Use "Ctrl+D" to exit.

prompt>
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
> filter ( ?age > 20 ) };;
```

* SHOW PREFIXES

```
prompt> show prefixes;;
```

* SET PREFIX

```
prompt> set prefix "smp" with namespace "http://example.org/simple#";;
```

Insert two same characters consecutively to end the command (e.g., `;;`, `  `, etc).

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
