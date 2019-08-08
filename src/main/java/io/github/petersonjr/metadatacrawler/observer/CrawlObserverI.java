package io.github.petersonjr.metadatacrawler.observer;

import io.github.petersonjr.metadatacrawler.model.JdbcDatasource;

public interface CrawlObserverI {

	public void observe(JdbcDatasource catalog);
	
	public void crawlEnded();
	
	public void crawlStart();
	
}
