package io.github.petersonjr.metadatacrawler;

public class MetadataCrawlerConfig {
	//https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
	
	private final String jdbcUrl;
	private final String user;
	private final String password;
	private final String catalogs_regex;
	private final Boolean onlyDefault;
	
	private MetadataCrawlerConfig(Builder builder) {
		this.jdbcUrl = builder.jdbcUrl;
		this.user = builder.user;
		this.password = builder.password;
		this.catalogs_regex = builder.catalogs_regex;
		this.onlyDefault = builder.onlyDefault;
	}
	
	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getCatalogs_regex() {
		return catalogs_regex;
	}

	public Boolean getOnlyDefault() {
		return onlyDefault;
	}


	public static class Builder {
		private final String jdbcUrl;
		private String user;
		private String password;
		private String catalogs_regex;
		private Boolean onlyDefault;
		
		public Builder(String jdbcUrl) {
			this.jdbcUrl = jdbcUrl;
		}
		
		public Builder user(String user) {
			this.user = user;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder catalogs_regex(String catalogs_regex) {
			if(catalogs_regex!=null) {
				this.catalogs_regex = catalogs_regex.trim();
			}
			return this;
		}
		
		public Builder onlyDefault(Boolean onlyDefault) {
			this.onlyDefault = onlyDefault;
			return this;
		}
		
		public MetadataCrawlerConfig build() {
			MetadataCrawlerConfig config = new MetadataCrawlerConfig(this);
			return config;
		}
	}
	
	
}
