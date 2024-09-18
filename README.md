# JQA Sample

This is a sample project to show some concepts of jQAssistant.

## How to start
You need Maven and Java 22
then run
```
mvn verify
```
To build the project, execute jQAssistant and create the documentation.
The documentation can be found in `/target/docs` as a multipage html side.

To start the neo4j server run `mvn jqassistant:server` and open `http://localhost:7474?dbms=bolt://localhost:7687&preselectAuthMethod=NO_AUTH` in your browser.

For mor information about jQAssistant visit the [official documentation](https://jqassistant.github.io/jqassistant/current)

