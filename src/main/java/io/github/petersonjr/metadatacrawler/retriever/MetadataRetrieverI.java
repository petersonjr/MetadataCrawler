package io.github.petersonjr.metadatacrawler.retriever;

import java.sql.SQLException;
import java.util.List;

import io.github.petersonjr.metadatacrawler.model.JdbcDatasource;
import io.github.petersonjr.metadatacrawler.model.Schema;

public interface MetadataRetrieverI {

	public JdbcDatasource getDataSource(String catalogName) throws SQLException; 
	
	public List<Schema> getSchemas(String catalogName, boolean onlyDefault) throws SQLException;
	
	public List<String> getCatalogsNames();
	
}
