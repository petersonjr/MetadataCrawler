package io.github.petersonjr.metadatacrawler.model;

import java.util.LinkedList;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class AvroSchemaBuilder {

	public static void main(String[] args) throws Exception {

		Schema columnSchema = SchemaBuilder.record("Column").namespace("io.github.petersonjr.metadatacrawler.model")
				.doc("A table column")
				.fields()
				.name("name").doc("The column name").type().stringType().noDefault()
				.name("qualified_name").doc("The column's qualified name").type().optional().stringType()
				.name("number").doc("The column number (0-based)").type().optional().intType()
				.name("size").doc("The column size").type().optional().intType()
				.name("nullable").doc("Is the column nullable?").type().optional().booleanType()
				.name("primary_key").doc("Is the column a primary key?").type().optional().booleanType()
				.name("indexed").doc("Is the column indexed?").type().optional().booleanType()
				.name("column_type").doc("The column type").type().optional().stringType()
				.name("native_type").doc("The native column type (as defined by the datasource itself)").type().optional().stringType()
				.name("remarks").doc("Any remarks on the column").type().optional().stringType()
				.endRecord();

		Schema tableSchema = SchemaBuilder.record("Table").namespace("io.github.petersonjr.metadatacrawler.model")
				.doc("A table, which usually belongs to a schema and has columns")
				.fields()
				.name("name").doc("The table name").type().stringType().noDefault()
				.name("type").doc("The table type (VIEW, TABLE, etc)").type().optional().stringType()
				.name("qualified_name").doc("The table's qualified name").type().optional().stringType()
				.name("remarks").doc("Any remarks on the table").type().optional().stringType()
				//.name("columns").doc("The table's columns").type().array().items(columnSchema).arrayDefault(new LinkedList<Object>())
				.name("columns").doc("The table's columns").type().optional().array().items(columnSchema)
				.endRecord();

		Schema stringSchema = Schema.create(Schema.Type.STRING);
		Schema relationshipSchema = SchemaBuilder.record("Relationship").namespace("io.github.petersonjr.metadatacrawler.model")
				.doc("Relationship between two tables, where one set of columns is the primary key, and another set is the foreign key")
				.fields()
				.name("primary_table").doc("The table of the primary key column(s)").type().stringType().noDefault()
				.name("primary_columns").doc("The primary key columns of this relationship").type().array().items(stringSchema).arrayDefault(new LinkedList<Object>())
				.name("foreign_table").doc("The table of the foreign key column(s).").type().stringType().noDefault()
				.name("foreign_columns").doc("The foreign key columns of this relationship").type().array().items(stringSchema).arrayDefault(new LinkedList<Object>())
				.endRecord();
		
		//TODO: Descrever melhor o que quer dizer schema aqui
		Schema schemaSchema = SchemaBuilder.record("Schema").namespace("io.github.petersonjr.metadatacrawler.model")
				.doc("A catalog's schema")
				.fields()
				.name("name").doc("The schema name").type().stringType().noDefault()
				.name("qualified_name").doc("The schemas='s qualified name").type().optional().stringType()
				//.name("remarks").doc("Any remarks on the schema").type().optional().stringType()
				//.name("tables").doc("The schemas' tables").type().array().items(tableSchema).arrayDefault(new LinkedList<Object>())
				.name("tables").doc("The schemas' tables").type().optional().array().items(tableSchema)
				.name("relationships").doc("The schemas' tables relationships").type().optional().array().items(relationshipSchema)
				.endRecord();

		// https://cwiki.apache.org/confluence/display/METAMODEL/Schema+model
		// https://stackoverflow.com/questions/7942520/relationship-between-catalog-schema-user-and-database-instance
		// Observar Squirrel
		// TODO: Descrição jdbcdatasource como catálogo. Utilizar url como modelo.
		Schema dataSourceSchema = SchemaBuilder.record("JdbcDatasource").namespace("io.github.petersonjr.metadatacrawler.model")
				.doc("A record representing a jdbc datasource, which contains one or more schemas as defined by JDBC")
				.fields()
				//.name("identifier").doc("A generated identifier with the format server:port:catalog").type().stringType().noDefault()
				.name("db_url").doc("The jdbc url used to connect to the database").type().stringType().noDefault()
				.name("db_server").doc("The database server").type().optional().stringType()
				.name("db_port").doc("The database port").type().optional().stringType()
				.name("db_product_name").doc("The database product name").type().optional().stringType()
				.name("db_product_version").doc("The database product version").type().optional().stringType()
				.name("crawl_tool").doc("The name of the tool used to crawl the database").type().optional().stringType()
				.name("crawl_ts").doc("The timestamp of the crawl").type().optional().stringType()
				.name("user_name").doc("The user name used to connect to the database").type().stringType().noDefault()
				.name("catalog_name").doc("The name of the catalog").type().stringType().noDefault()
				.name("has_error").doc("Any error ocurred while crawling the catalog?").type().optional().booleanType()
				.name("error_msg").doc("If error ocurred, what's the error msg?").type().optional().stringType()
				.name("remarks").doc("Any remarks on the catalog").type().optional().stringType()
				//.name("schemas").doc("The catalog's schemas").type().array().items(schemaSchema).arrayDefault(new LinkedList<Object>())
				.name("schemas").doc("The catalog's schemas").type().optional().array().items(schemaSchema)
				.endRecord();
		
		System.out.println(dataSourceSchema.toString(true));
	}

	
}
