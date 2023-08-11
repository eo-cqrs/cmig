[![Managed By Self XDSD](https://self-xdsd.com/b/mbself.svg)](https://self-xdsd.com/p/eo-cqrs/cmig?provider=github)

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](https://www.rultor.com/b/eo-cars/cmig)](https://www.rultor.com/p/eo-cqrs/cmig)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)
<br>

[![mvn](https://github.com/eo-cqrs/cmig/actions/workflows/mvn.yml/badge.svg)](https://github.com/eo-cqrs/cmig/actions/workflows/mvn.yml)
[![maven central](http://maven-badges.herokuapp.com/maven-central/io.github.eo-cqrs/cmig/badge.svg)](https://search.maven.org/artifact/io.github.eo-cqrs/cmig)
[![javadoc](https://javadoc.io/badge2/io.github.eo-cqrs/cmig/javadoc.svg)](https://javadoc.io/doc/io.github.eo-cqrs/cmig)
[![codecov](https://codecov.io/gh/eo-cqrs/cmig/branch/master/graph/badge.svg?token=X1M9j0etoQ)](https://codecov.io/gh/eo-cqrs/cmig)

[![Hits-of-Code](https://hitsofcode.com/github/eo-cqrs/cmig)](https://hitsofcode.com/view/github/eo-cqrs/cmig)
[![Lines-of-Code](https://tokei.rs/b1/github/eo-cqrs/cmig)](https://github.com/eo-cqrs/cmig)
[![PDD status](http://www.0pdd.com/svg?name=eo-cqrs/cmig)](http://www.0pdd.com/p?name=eo-cqrs/cmig)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/eo-cqrs/cmig/blob/master/LICENSE.txt)

Project architect: [@h1alexbel](https://github.com/h1alexbel)

CMIG: Schema Migration tool for [Apache Cassandra](https://cassandra.apache.org/_/index.html).

**Motivation**. We are lacking off with schema version control and automatic table creation in Cassandra.
CMIG is suggesting schema control tool for that.

**Principles**. These are the [design principles](https://www.elegantobjects.org/#principles) behind CMIG.

**How to use**. All you need is this (get the latest version [here](https://search.maven.org/artifact/io.github.eo-cqrs/cmig)):

Maven:
```xml
<dependency>
  <groupId>io.github.eo-cqrs</groupId>
  <artifactId>cmig</artifactId>
</dependency>
```

In your resources directory you should create a `cmig` directory,
and inside it new XML file, for instance `master.xml`:
```xml
<states>
  <changeState id="1" author="h1alexbel">
    <files>
      <file path="001-initial-keyspace.cql"/>
      <file path="002-queries-table.cql"/>
    </files>
  </changeState>
  <changeState id="2" author="h1alexbel">
    <files>
      <file path="003-stuff.cql"/>
    </files>
  </changeState>
</states>
```

**Inside all the files you must have only one operation**.
Decompose files for better traceability.

In Java, all you need is to create Cassandra and Master objects:
```java
final Cassandra cassandra = new Simple("localhost", 9042);
final String sha = new Master("master.xml", cassandra).value();
```

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install
```

You will need Maven 3.8.7+ and Java 17+.

Our [rultor image](https://github.com/eo-cqrs/eo-kafka-rultor-image) for CI/CD.
