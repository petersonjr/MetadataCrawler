package io.github.petersonjr.metadatacrawler.retriever;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.metamodel.jdbc.JdbcDataContext;
import org.apache.metamodel.schema.NamedStructure;

import io.github.petersonjr.metadatacrawler.MetadataCrawlerConfig;
import io.github.petersonjr.metadatacrawler.model.Schema;

/**
 * <p>
 * Databases like SQLServer, PostgreSQL and Informix have an object identified by:
 * <br/>
 * (database name + (schema|owner) name + object name)
 * 
 * <p>
 * They each have their own semantics in respect to schema and catalogs while dealing with JDBC.
 * Take a look at https://stackoverflow.com/questions/7942520/relationship-between-catalog-schema-user-and-database-instance
 *
 * <p>
 * Thus, we have:
 * <br/>
 * In PostgreSQL:
 * <li>server instance == db cluster == all data managed by same execution engine
 * <li>database == catalog == single database within db cluster, isolated from other databases in same db cluster
 * <li>schema == namespace within database
 * <li>user == named account, who can connect to database, own and use objects in each allowed database separately
 * <li>to identify any object in running server, you need (database name + schema name + object name)
 * <br/>
 * In Microsoft SQL Server:
 * <li>server instance == set of managed databases
 * <li>database == namespace qualifier within the server, rarely referred to as catalog
 * <li>schema == owner == namespace within the database, tied to database roles, by default only dbo is used
 * <li>user == named account, who can connect to server and use (but can not own - schema works as owner) objects in one or more databases
 * <li>to identify any object in running server, you need (database name + owner + object name)
 * <br/>
 * <p>
 * Therefore, in this class we implement methods to get catalogs and schemas that respect those definitions 
 * 
 *
 */
public class MetadataRetrieverB extends MetatadaRetrieverAbs {

	private static final Logger LOGGER = Logger
		    .getLogger(MetadataRetrieverB.class.getName());
	
	MetadataRetrieverB(MetadataCrawlerConfig config) {
		super(config);
	}

	@Override
	public List<Schema> getSchemas(String catalogName, boolean onlyDefault) throws SQLException {
		List<Schema> schemas = new LinkedList<io.github.petersonjr.metadatacrawler.model.Schema>();
		List<org.apache.metamodel.schema.Schema> schemas_to_crawl;
		
		JdbcDataContext context = getDataContext(catalogName);
		
		// Decision to crawl all schemas from catalogs or just the default schema
		schemas_to_crawl = onlyDefault ? Arrays.asList(context.getDefaultSchema()) :
				context.getSchemas();
		
		for(org.apache.metamodel.schema.Schema s : schemas_to_crawl) {
			schemas.add(getSchema(s));
		}
		return schemas;
	}

	@Override
	public List<String> getCatalogsNames() {
		return Arrays.asList(getDefaultDataContext().getCatalogNames());
	}
	
	@Override
	protected String getQualifiedLabel(NamedStructure object) {
		if(getCurrentDataContext()!=null) {
			String catalogName = getCurrentDataContext().getCatalogName();
			return catalogName + "." + object.getQualifiedLabel();
		}else {
			return object.getQualifiedLabel();
		}
	}
}