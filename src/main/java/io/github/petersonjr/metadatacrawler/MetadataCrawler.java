package io.github.petersonjr.metadatacrawler;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import io.github.petersonjr.metadatacrawler.model.JdbcDatasource;
import io.github.petersonjr.metadatacrawler.model.Schema;
import io.github.petersonjr.metadatacrawler.observer.CrawlObserverI;
import io.github.petersonjr.metadatacrawler.retriever.MetadataRetrieverI;
import io.github.petersonjr.metadatacrawler.retriever.MetadataRetriverBuilder;


public final class MetadataCrawler {
	
	// Important: https://stackoverflow.com/questions/7942520/relationship-between-catalog-schema-user-and-database-instance
	
	private static final Logger LOGGER = Logger
		    .getLogger(MetadataCrawler.class.getName());
		
	private MetadataRetrieverI metadataRetriever;
	private MetadataCrawlerConfig config;
	
	public MetadataCrawler(MetadataCrawlerConfig config) {
		this.metadataRetriever = MetadataRetriverBuilder.build(config);
		this.config = config;
	}
	
	
	/**
	 * Crawls a database and return info about each catalog, including 
	 * catalog name and status of the user being able to read the catalog schemas.
	 * Also, includes info about the current user, the db and crawl info.
	 * @param conn The already alive connection to the database
	 * @param observers A list of observers that get called when a new catalog is found
	 * @return A list of catalogs infos
	 * @throws SQLException
	 */
	public List<JdbcDatasource> crawl(List<CrawlObserverI> observers,
			boolean includeSchemaInfo)
					throws SQLException {
		if(observers==null) {
			observers = new LinkedList<CrawlObserverI>();
		}
		
		for(CrawlObserverI observer : observers) {
			observer.crawlStart();
		}
		
		List<String> catalogsNames = metadataRetriever.getCatalogsNames();
		List<JdbcDatasource> catalogs = new LinkedList<JdbcDatasource>();

		for (final String catalogName: catalogsNames) {
			if(StringUtils.isNotEmpty(config.getCatalogs_regex())) {
				if(!catalogName.matches(config.getCatalogs_regex())) {
					LOGGER.log(Level.INFO, String.format("Skipping <%s>", catalogName));
					continue;
				}
			}
			LOGGER.log(Level.INFO, String.format("Processing <%s>", catalogName));
			
			JdbcDatasource dataSource = metadataRetriever.getDataSource(catalogName);
			
			if(includeSchemaInfo) {
				populateSchemasInfo(dataSource);
			}
			
			//System.out.println(catalogInfo);
			catalogs.add(dataSource);
			
			for(CrawlObserverI observer : observers) {
				observer.observe(dataSource);
			}
		}
		
		for(CrawlObserverI observer : observers) {
			observer.crawlEnded();
		}
		
		return catalogs;
	}
	
	
	private JdbcDatasource populateSchemasInfo(JdbcDatasource dataSource) {
		String catalogName = dataSource.getCatalogName().toString();
		
		try {
			List<Schema> schemas = metadataRetriever.getSchemas(catalogName, config.getOnlyDefault());
			LOGGER.log(Level.INFO, String.format("Schemas: %d", schemas.size()));
			dataSource.setSchemas(schemas);
			dataSource.setHasError(false);
			
		} catch (Exception e) {
			LOGGER.log(Level.INFO,
					String.format("Error acessing %s: %s", catalogName, e.getLocalizedMessage()));
			dataSource.setHasError(true);
			dataSource.setErrorMsg(e.getLocalizedMessage());
		}
		
		return dataSource;
	}


}
