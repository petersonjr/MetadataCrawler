package io.github.petersonjr.metadatacrawler.retriever;

import io.github.petersonjr.metadatacrawler.MetadataCrawlerConfig;

public class MetadataRetriverBuilder {

	public static MetadataRetrieverI build(MetadataCrawlerConfig config) {
		MetadataRetrieverA retrieverA = new MetadataRetrieverA(config);
		String productName = retrieverA.getDefaultDataContext().getDatabaseProductName().toLowerCase();
		
		if(productName.contains("oracle") || productName.contains("mysql")) {
			return retrieverA;
		} else { //sql server, informix, postgreSQL
			//retrieverA.getDefaultDataContext().getConnection().close();
			return new MetadataRetrieverB(config);
		}
	}
	
}
