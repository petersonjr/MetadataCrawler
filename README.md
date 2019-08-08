# MetadataCrawler

A simple tool to extract metadata from relational databases

## General Info

MetadataCrawler is yet another tool to extract metadata from relational database management systems. It connects to a RDMS and extracts metadata via JDBC, making use of [Apache Metamodel](https://metamodel.apache.org/). MetadataCrawler collects information about all catalogs, schemas, tables and collumns of a RDMS and produce output in avro or json files.

## Why?

There are great tools that do the same thing(and much more) as MC, such as [SchemaCrawler](https://www.schemacrawler.com/) or [SchemaSpy](http://schemaspy.org/). In fact, I recommend you to take a look at them before trying MetadataCrawler. The motivations to build MC were:

- Crawl all catalogs from a RDMS at once. Mostly, the tools available only gets information from a catalog at a time.
- Produce output that is easy to process. I found avro format a good option for this. Avro forces you to define a schema, which serves not only as documentation, but also as a input  to [generate code to process data in several languages](https://avro.apache.org/docs/1.8.2/index.html).

In short, MetadataCrawler has two main goals: to easily collect metadata from all catalogs of a RDMS and produce information in a simple unified schema that is easy to process.

As a bonus, check how you can use [hive to process data generated with MetadataCrawler](hive.md).

## How to use

MetadataCrawler is run as a standalone java application. Just download the latest release and run:

```
java -jar simple-metadata-crawler.jar --url  "jdbc:mysql://localhost:33306" -u root -p "metadata" --format json -o mysql.json
```

**Make sure that the database user has reading(select) permissions on the catalogs you want to get metadata from. See more in [here](https://github.com/schemacrawler/SchemaCrawler/issues/256#issuecomment-509652994).**

You may also filter catalogs using a regex expression:

```
java -jar simple-metadata-crawler.jar --url jdbc:sqlserver://localhost:1433 --catalogs "EMPLOYEES|SALES" -u root -p "pass"  --format json -o sqlserver_f.json
```

To see the full list of options just run:

```
java -jar simple-metadata-crawler-0.1.0.jar --help
```

The tool is bundled with some JDBC drivers for convenience, namely SQL Server, Mysql and PostgreSQL drivers. **If you want to run it with other JDBC drivers such as Oracle or Informix, you have to generate a jar with those bundled dependencies. Just follow the instructions at [build-session].**


### Password param

You can inform the user password via command line(as the previous example), interactively or via environment variable:

Interactively:

```
java -jar simple-metadata-crawler.jar --url  "jdbc:mysql://localhost:33306" -u root -p --format json -o mysql.json
```
 
Via environment variable(which is [safer](https://picocli.info/#_interactive_password_options)):

 export JDBC_PASSWORD="pass" && java -jar simple-metadata-crawler.jar "jdbc:mysql://localhost:33306" -u root --format json -o mysql.json
 
Defining the environment variable:

```
export MYSQLPASS="pass" && java -jar simple-metadata-crawler.jar --url "jdbc:mysql://localhost:33306" -u root --password:env MYSQLPASS --format avro -o mysql.avro
```

### SQL Server execution with Integrated Authentication On Windows

It is possible to connect to SQL Server  with integrated authentication on windows. Following the [official instructions](https://docs.microsoft.com/pt-br/sql/connect/jdbc/building-the-connection-url?view=sql-server-2017):

* Copy sqljdbc_auth.dll file to a directory on your system, or find where it is located.
* Run MetadataCrawler setting java.library.path property as the directory where the dll file is located. Also make sure to use IntegratedSecurity=true on the jdbc url:

```
java -Djava.library.path="C:\Microsoft JDBC Driver 6.4 for SQL Server\sqljdbc_<version>\enu\auth\x86" -jar simple-metadata-crawler.jar --url  "jdbc:sqlserver://server:port;IntegratedSecurity=true" -u root -p "pass" --format json -o sqlserver.json
```

## Batch run

To easily get started on running a crawl for several servers in batch, you can make use of **mcrawl_batch.sh** file, which define a folder structure and facilitates the execution of MetadataCrawler for more than one source.

Just follow the steps:

* Edit the following line of **mcrawl_batch.sh** file to match your environment:

```
CMD="java -jar target/simple-metadata-crawler-0.1.0.jar"
```

Create a **run_crawl.sh** file, as the example below:

```
#!/bin/bash


DATADIR="./data"
source mcrawl_batch.sh

# The params for crawl function are: jdbc_url, user, password, output_file_name, catalogs_filter

# Mysql
crawl "jdbc:mysql://localhost:3306" "user" "password" "mysql_localhost"

# SQl server
crawl "jdbc:sqlserver://localhost:1433" "root" "passwd" "sqlserver_localhost" "EMPLOYEES|EVENTS"
```
This file just separates the params of crawl executions from the crawling code. Run it:

```
./run_crawl.sh
```
Check the output folder:
```
data/
  2019-08-02/
    mysql_localhost.avro
    mysql_localhost.log
    sqlserver_localhost.avro
    sqlserver_localhost.log
```

## The output schema

Take a look at the avro [schema](src/main/resources/avro/JdbcDatasource.avsc) used by MetadataCrawler. In short, the schema was inspired by Apache Metamodel and JDBC definitions. A high level description of the schema is:

```
DataSource
  Catalog
    Schema
      Tables
        Columns
      Relationships
```

A DataSource is defined by a JDBC Url, and has properties such as url, server_name, user, db_product_name, etc. Each DataSource has one or more catalogs, a catalog has one or more schemas, and so on. Notice that [for each database vendor, there are different meanings of JDBC definitions and how to identify an object in a database](https://stackoverflow.com/questions/7942520/relationship-between-catalog-schema-user-and-database-instance). Next, an explanation of what a Catalog and Schema means to MetadataCrawler.

### Catalogs and Schemas

There are [different meanings for what catalog and schema means for each database vendor](https://stackoverflow.com/questions/7942520/relationship-between-catalog-schema-user-and-database-instance). For instance, see the differences between Oracle and SQLServer:

**In Oracle:**

* server instance == database == catalog == all data managed by same execution engine
* schema == namespace within database, identical to user account
* user == schema owner == named account, identical to schema, who can connect to database, who owns the schema and use objects possibly in other schemas
* to identify any object in running server, you need (schema name + object name)

**In Microsoft SQL Server:**


* server instance == set of managed databases
database == namespace qualifier within the server, rarely referred to as catalog
* schema == owner == namespace within the database, tied to database roles, by default only dbo is used
* user == named account, who can connect to server and use (but can not own - schema works as owner) objects in one or more databases
* to identify any object in running server, you need (database name + owner + object name)

Therefore, the following decisions were made:

* For RDMS like Oracle or Mysql, in which an object may be identified by (schema name + object name), the objects will be outputed as part of a schema and of a catalog with the same name. For instance, a table EVENT of schema COMPETITIONS will be outputed as COMPETITIONS(catalog) -> COMPETITIONS(schema) -> EVENT(table).
* For RDMS like SqlServer or PostgreSQL, in which an object may be identified by (catalog name + schema name + object name), the objects will be outputed as part of a catalog and schema with the respective names. For instance, a table EVENT of schema DBO and catalog COMPETITIONS will be outputed as COMPETITIONS(catalog) -> DBO(schema) -> EVENT(table).
* An object qualified name respects the qualified name informed by the database vendor. For instance, a column qualifed name in SQL Server will be (database name + schema + table name + column name), where in Oracle it will be (database name + table name + column name).

## Hive analysis

You can use hive to [quickly process data obtained with Metadata](hive.md).

## Build Instructions

Make sure you have Apache Maven. There are two profiles in the maven project:

* open-drivers: profile with just open source jdbc drivers dependencies (mysql, postgresql, sqlserver)
* all-drivers: profile all jdbc drivers dependencies (+ oracle, inforimix)

To build, just choose the profile you want:
```
mvn package -P open-drivers
```

or

```
mvn package -P all-drivers
```

To build with all drivers, you need to install oracle jdbc driver as a maven dependency: [How to add Oracle JDBC driver in your Maven local repository](https://www.mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/).

``` 
mvn install:install-file -Dfile=path/to/your/ojdbc7.jar -DgroupId=com.oracle 
	-DartifactId=ojdbc7 -Dversion=12.2.0.1 -Dpackaging=jar
```

## Development

### Eclipse project

To create an Eclipse project:

```
mvn eclipse:clean
mvn eclipse:eclipse -P all-drivers -DdownloadSources=true -DdownloadJavadocs=true 
```

Make sure to set [UTF-8 enconding](https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse) [in eclipse](https://stackoverflow.com/questions/4043634/define-eclipse-project-encoding-as-utf-8-from-maven).

### Avro schema

The avro schema file is generated with the help of [AvroSchemaBuilder](src/main/java/io/github/petersonjr/metadatacrawler/model/AvroSchemaBuilder.java) class. No one deserves to manually write a json schema.

### Generate avro classes

To generate [Java classes](src/main/java/io/github/petersonjr/metadatacrawler/model) based on the schema, just use maven:

```
mvn clean compile
``` 

## License

MetadataCrawler is distributed under [GNU General Public License v3](http://www.gnu.org/licenses/gpl-3.0.en.html).

MetadataCrawler
Copyright (C) 2019  Péterson Júnior <peterson.junior@gmail.com>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
