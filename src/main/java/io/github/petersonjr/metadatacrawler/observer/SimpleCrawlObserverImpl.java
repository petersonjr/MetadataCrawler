package io.github.petersonjr.metadatacrawler.observer;

import io.github.petersonjr.metadatacrawler.model.JdbcDatasource;

public class SimpleCrawlObserverImpl implements CrawlObserverI {

	@Override
	public void observe(JdbcDatasource catalog) {
		System.out.println("Crawled " + catalog.getCatalogName());
	}

	@Override
	public void crawlEnded() {
		System.out.println("Crawl ended!");
		
	}

	@Override
	public void crawlStart() {
		System.out.println("Crawl started!");
	}

}
