## Hive

You can use hive to quickly process data obtained with MetadataCrawler. Just follow the stpes below.


* Upload the schema file to hdfs

```
java -jar simple-metadata-crawler-0.1.0.jar --schema > JdbcDatasource.avsc

hdfs dfs -put -f JdbcDatasource.avsc /dbs_metadata/
```

* Upload the avro files to hdfs. In the following example, the avro files are located in local folder data/2019/08/08

```
hdfs dfs -rm -f  /dbs_metadata/raw/*

hdfs dfs -put data/2019-08-08/* /dbs_metadata/raw/

```
* In Hive - Criate external table

```
drop table dbs_metadata;
drop view dbs_metadata_extended;

CREATE EXTERNAL TABLE dbs_metadata
  ROW FORMAT SERDE
  'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
  STORED AS INPUTFORMAT
  'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
  OUTPUTFORMAT
  'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
  LOCATION
  'hdfs:///dbs_metadata/raw'
  TBLPROPERTIES (
    'avro.schema.url'='hdfs:///dbs_metadata/JdbcDatasource.avsc');
```

* In Hive - Criate view

```
CREATE VIEW dbs_metadata_extended AS
SELECT db_url, db_server, db_port, db_product_name, db_product_version, crawl_tool, crawl_ts, user_name, catalog_name, remarks as catalog_remarks, has_error, error_msg,
s_schema.name as schema_name, s_schema.qualified_name as schema_qualified_name,
s_table.name as table_name, s_table.type as table_type, s_table.qualified_name as table_qualified_name, s_table.remarks as table_remarks, 
s_column.name as column_name, s_column.qualified_name as column_qualified_name, s_column.number as column_number, s_column.primary_key as column_is_primary_key, s_column.indexed as column_is_indexed, s_column.column_type as column_type, s_column.native_type as column_native_type, s_column.remarks as column_remarks
FROM dbs_metadata 
LATERAL VIEW OUTER explode(schemas) t_schema_t AS s_schema
LATERAL VIEW OUTER explode(s_schema.tables) t_table AS s_table
LATERAL VIEW OUTER explode(s_table.columns) t_column AS s_column;
```

* Execute some queries

```
select distinct db_server, db_port, db_product_name, catalog_name, schema from dbs_metadata_extended;

select db_url, catalog_name, schema, tabl, col, col_number, col_type from dbs_metadata_extended where schema='dbo' and lower(col) LIKE 'nom\_%' limit 50;
``` 

* You may also create another optimized table, and filtering system schemas and catalogs

```
CREATE EXTERNAL TABLE IF NOT EXISTS dbs_metadata_dm (
db_url STRING,
db_server STRING,
db_port STRING,
db_product_name  STRING,
db_product_version STRING,
crawl_tool STRING,
crawl_ts STRING,
user_name STRING,
catalog_name STRING,
catalog_remarks STRING,
has_error BOOLEAN,
error_msg STRING,
schema_name STRING,
schema_qualified_name STRING,
table_name STRING,
table_type STRING,
table_qualified_name STRING,
table_remarks STRING,
column_name STRING,
column_qualified_name STRING,
column_number  STRING,
column_is_primary_key  STRING,
column_is_indexed  STRING,
column_type  STRING,
column_native_type  STRING,
column_remarks  STRING
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ';'
STORED AS ORC 
LOCATION 'hdfs:///dbs_metadata/dbs_metadata_t'
TBLPROPERTIES ("orc.bloom.filter.columns"="schema_name,table_name,column_name");


INSERT OVERWRITE TABLE dbs_metadata_dm
SELECT db_url,
db_server,
db_port,
db_product_name,
db_product_version,
crawl_tool,
crawl_ts,
user_name,
catalog_name,
catalog_remarks,
has_error,
error_msg,
schema_name,
schema_qualified_name,
table_name,
table_type,
table_qualified_name,
table_remarks,
column_name,
column_qualified_name,
column_number,
column_is_primary_key,
column_is_indexed,
column_type,
column_native_type,
column_remarks
FROM dbs_metadata_extended
WHERE schema_name not in ('INFORMATION_SCHEMA',
'sys',
'SCOTT',
'AUDSYS',
'CTXSYS',
'DBSNMP',
'EXFSYS',
'MDDATA',
'MDSYS',
'ORDSYS',
'OUTLN',
'SI_INFORMTN_SCHEMA',
'SYSBACKUP',
'SYSDG',
'SYSKM',
'SYSMAN',
'SYSTEM',
'WMSYS',
'XDB',
'XS$NULL') OR 
catalog_name NOT IN ('ANONYMOUS',
'APEX_030200',
'APEX_040200',
'APEX_PUBLIC_USER',
'APPQOSSYS',
'ATIVAS',
'AUDSYS',
'CTXSYS',
'dbatools',
'DVSYS',
'EXFSYS',
'GDOSYS',
'master',
'MDDATA',
'MDSYS',
'model',
'msdb',
'ORDDATA',
'ORDSYS',
'OUTLN',
'SCOTT',
'SYS',
'SYSBACKUP',
'SYSDG',
'SYSKM',
'SYSMAN',
'SYSTEM',
'tempdb',
'WMSYS',
'WSRR',
'XS$NULL');
```