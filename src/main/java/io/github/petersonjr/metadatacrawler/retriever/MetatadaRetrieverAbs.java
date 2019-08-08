/**
 * 
 */
package io.github.petersonjr.metadatacrawler.retriever;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.jdbc.JdbcDataContext;
import org.apache.metamodel.schema.NamedStructure;
import org.apache.metamodel.schema.TableType;

import io.github.petersonjr.metadatacrawler.MetadataCrawlerConfig;
import io.github.petersonjr.metadatacrawler.model.Column;
import io.github.petersonjr.metadatacrawler.model.JdbcDatasource;
import io.github.petersonjr.metadatacrawler.model.Relationship;
import io.github.petersonjr.metadatacrawler.model.Schema;
import io.github.petersonjr.metadatacrawler.model.Table;
import io.github.petersonjr.metadatacrawler.util.JdbcUrlParser;

/**
 * 
 *
 */
public abstract class MetatadaRetrieverAbs implements MetadataRetrieverI {
	
	private MetadataCrawlerConfig config;
	private JdbcDatasource defaultJdbcDataSource;
	private JdbcDataContext defaultDataContext;
	private JdbcDataContext catalogsDataContext;
	private JdbcDataContext currentDataContext;
	
	/**
	 * 
	 */
	protected MetatadaRetrieverAbs(MetadataCrawlerConfig config) {
		this.config = config;
	}

	protected JdbcDataContext getDefaultDataContext() {
		if(defaultDataContext==null) {
			final DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
			properties.put("type", "jdbc");
			properties.put("url", config.getJdbcUrl());
			properties.put("username", config.getUser());
			properties.put("password", config.getPassword());
			
			DataContext dataContext = DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties);
			JdbcDataContext context = (JdbcDataContext) dataContext;
			this.defaultDataContext = context;
		}
		
		return this.defaultDataContext;
	}
	
	protected JdbcDataContext getDataContext(String catalogName) throws SQLException {
		if(catalogsDataContext==null) {
			final DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
			properties.put("type", "jdbc");
			properties.put("url", config.getJdbcUrl());
			properties.put("username", config.getUser());
			properties.put("password", config.getPassword());
			properties.put("catalog", catalogName);

			DataContext dataContext = DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties);
			JdbcDataContext context = (JdbcDataContext) dataContext;
			this.catalogsDataContext = context;
		}
		
		Connection conn = catalogsDataContext.getConnection();
		conn.setCatalog(catalogName);
		catalogsDataContext = new JdbcDataContext(conn, TableType.DEFAULT_TABLE_TYPES, catalogName);
		
		this.currentDataContext = catalogsDataContext;
		return catalogsDataContext;
	}
	
	protected JdbcDataContext getCurrentDataContext() {
		return currentDataContext;
	}

	protected abstract String getQualifiedLabel(NamedStructure object);
	
	@Override
	public JdbcDatasource getDataSource(String catalogName) throws SQLException {
		JdbcDatasource dataSource = JdbcDatasource.newBuilder(getDefaultJdbcDatasource()).build();
		dataSource.setCatalogName(catalogName);
		return dataSource;
	}
	
	private JdbcDatasource getDefaultJdbcDatasource() throws SQLException {
		if(this.defaultJdbcDataSource!=null) {
			return this.defaultJdbcDataSource;
		}
		
		Connection conn = getDefaultDataContext().getConnection();
		
		// if we cant't extract server from jdbc url, consider full url as server
		String dbServer = JdbcUrlParser.getHost(conn.getMetaData().getURL());
		dbServer = StringUtils.isEmpty(dbServer) ? config.getJdbcUrl() : dbServer;
		
		String dbPort = Integer.valueOf(JdbcUrlParser.getPort(conn.getMetaData().getURL())).toString();
		
		JdbcDatasource dataSource = JdbcDatasource.newBuilder()
				//.setIdentifier(String.format("%s:%s:%s", crawlInfo.dbServer, crawlInfo.dbPort, catalogName))
				.setDbUrl(config.getJdbcUrl())
				.setDbServer(dbServer)
				.setDbPort(dbPort)
				.setDbProductName(conn.getMetaData().getDatabaseProductName())
				.setDbProductVersion(conn.getMetaData().getDatabaseProductVersion())
				.setCrawlTool("Simple Metadata Crawler - Apache MetaModel")
				.setCrawlTs(LocalDateTime.now().toString())
				.setUserName(conn.getMetaData().getUserName())
				.setCatalogName("-")
				.build();

		this.defaultJdbcDataSource = dataSource;
		return dataSource;
	}
	
	
	
	protected Schema getSchema(org.apache.metamodel.schema.Schema s) {
		Schema schema = Schema.newBuilder()
				.setName(s.getName())
				.build();
		schema.setQualifiedName(getQualifiedLabel(s));
		
		List<Table> tables = new LinkedList<Table>();
		for(org.apache.metamodel.schema.Table t : s.getTables()) {
			tables.add(getTable(t));
		}
		schema.setTables(tables);
		
		List<Relationship> relationships = new LinkedList<Relationship>();
		for(org.apache.metamodel.schema.Relationship r : s.getRelationships()) {
			relationships.add(getRelationship(r));
		}
		schema.setRelationships(relationships);
		
		return schema;
	}
	
	
	protected Relationship getRelationship(org.apache.metamodel.schema.Relationship r) {
		List<CharSequence> primaryColumns = new LinkedList<CharSequence>();
		List<CharSequence> foreignColumns = new LinkedList<CharSequence>();
		r.getPrimaryColumns().forEach(a -> primaryColumns.add(a.getName()));
		r.getForeignColumns().forEach(a -> foreignColumns.add(a.getName()));
		
		Relationship relationship = Relationship.newBuilder()
				.setPrimaryTable(r.getPrimaryTable().getName())
				.setPrimaryColumns(primaryColumns)
				.setForeignTable(r.getForeignTable().getName())
				.setForeignColumns(foreignColumns)
				.build();
		return relationship;
	}
	
	
	protected Table getTable(org.apache.metamodel.schema.Table t) {
		Table table = Table.newBuilder()
				.setName(t.getName())
				.setRemarks(t.getRemarks())
				.build();
		table.setQualifiedName(getQualifiedLabel(t));
		table.setType(t.getType().toString());
		
		List<Column> columns = new LinkedList<Column>();
		for(org.apache.metamodel.schema.Column c : t.getColumns()) {
			columns.add(getColumn(c));
		}
		
		table.setColumns(columns);
		return table;
	}
	
	
	protected Column getColumn(org.apache.metamodel.schema.Column c) {
		Column column = Column.newBuilder()
				.setName(c.getName())
				.setQualifiedName(getQualifiedLabel(c))
				.setNumber(c.getColumnNumber())
				.setSize(c.getColumnSize())
				.setNullable(c.isNullable())
				.setPrimaryKey(c.isPrimaryKey())
				.setIndexed(c.isIndexed())
				.setColumnType(c.getType().getName())
				.setNativeType(c.getNativeType())
				.setRemarks(c.getRemarks())
				.build();
		return column;
	}

}
