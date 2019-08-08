package io.github.petersonjr.metadatacrawler.retriever;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.metamodel.schema.NamedStructure;

import io.github.petersonjr.metadatacrawler.MetadataCrawlerConfig;
import io.github.petersonjr.metadatacrawler.model.Schema;

/**
 * <p>
 * Databases like Oracle and Mysql have an object identified by:
 * <br/>
 * (schema | database) name + object name
 * 
 * <p>
 * They each have their own semantics in respect to schema and catalogs while dealing with JDBC.
 * Take a look at https://stackoverflow.com/questions/7942520/relationship-between-catalog-schema-user-and-database-instance
 *
 * <p>
 * Thus, we have:
 * <br/>
 * In Oracle:
 * <li>server instance == database == catalog == all data managed by same execution engine
 * <li>schema == namespace within database, identical to user account
 * <li>user == schema owner == named account, identical to schema, who can connect to database, who owns the schema and use objects possibly in other schemas
 * <li>to identify any object in running server, you need (schema name + object name)
 * <br/>
 * In MySql:
 * <li>server instance == not identified with catalog, just a set of databases
 * <li>database == schema == catalog == a namespace within the server.
 * <li>user == named account, who can connect to server and use (but can not own - no concept of ownership) objects in one or more databases
 * <li>to identify any object in running server, you need (database name + object name)
 * 
 * <p>
 * Therefore, in this class we implement methods to get catalogs and schemas that respect those definitions 
 * 
 *
 */
public class MetadataRetrieverA extends MetatadaRetrieverAbs {

	private static final Logger LOGGER = Logger
		    .getLogger(MetadataRetrieverA.class.getName());
	
	MetadataRetrieverA(MetadataCrawlerConfig config) {
		super(config);
	}
	
	@Override
	public List<Schema> getSchemas(String catalogName, boolean onlyDefault)
			throws SQLException {
		org.apache.metamodel.schema.Schema s = this.getDefaultDataContext().getSchemaByName(catalogName);
		return Arrays.asList(getSchema(s));
	}

	@Override
	public List<String> getCatalogsNames() {
		List<String> catalogs = Arrays.asList(getDefaultDataContext().getCatalogNames());
		// Oracle semantics
		if(catalogs.isEmpty()) {
			catalogs = new LinkedList<String>();
			try {
				for(String s : getDefaultDataContext().getSchemaNames()) {
					catalogs.add(s);
				}
			} catch (Exception e) {
				LOGGER.log(Level.INFO, String.format("Error returning catalogs from oracle", e));
			}
		}
		return catalogs;
	}
	
	
	@Override
	protected String getQualifiedLabel(NamedStructure object) {
		return object.getQualifiedLabel();
//		if(getCurrentDataContext()!=null) {
//			String catalogName = getCurrentDataContext().getCatalogName();
//			return catalogName + "." + object.getQualifiedLabel();
//		}else {
//			return object.getQualifiedLabel();
//		}
	}
}