Semantika CLI2
==============
The next generation of Semantika CLI that provides an interactive command line utility for data querying and debugging.

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
