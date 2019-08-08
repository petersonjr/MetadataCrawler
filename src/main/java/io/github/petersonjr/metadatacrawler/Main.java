package io.github.petersonjr.metadatacrawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import io.github.petersonjr.metadatacrawler.observer.AvroOutputObserverImpl;
import io.github.petersonjr.metadatacrawler.observer.CrawlObserverI;
import io.github.petersonjr.metadatacrawler.observer.JsonOutputObserverImpl;
import io.github.petersonjr.metadatacrawler.observer.SimpleCrawlObserverImpl;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Visibility;
import picocli.CommandLine.ITypeConverter;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;

@Command(description = "%nSimple metadata crawler%n",
name = "metadata-crawler", mixinStandardHelpOptions = true, version = "metadata-crawler 1.0")
public class Main implements Callable<Void>{

	@Option(names = "--url", required = true, description = "The jdbc url to connect to the database")
	String jdbcUrl;
	
	@Option(names = "--catalogs", required = false, description = "A regex expression to filter catalogs")
	String catalogs_regex;
	
	@Option(names = "--only-default", required = false, description = "By default, metadata crawler crawls all schemas."
			+ " Turn this on to crawl only the default schema of each catalog.")
	Boolean onlyDefault = Boolean.FALSE;
	
	@Option(names = {"-u", "--user"}, required = true, description = "The database user")
	String user;
	
	@Option(names = {"-p", "--password"}, arity = "0..1", interactive = true, description = "The user password")
	String password;
	
    @Option(names = "--password:env", 
    		description = "An environment variable name. This is a more secure method to inform the password",
    		showDefaultValue = Visibility.ALWAYS)
    String passwordEnvironmentVariable = "JDBC_PASSWORD";
    
	@Option(names = "--log-level", showDefaultValue = Visibility.ALWAYS,
			converter = LevelTypeConverter.class, description = "The log level: "
			+ "SEVERE (highest value), WARNING, INFO, CONFIG, FINE, FINER, FINEST (lowest value) ")
	Level logLevel = Level.OFF;
	
	@Option(names = {"-o", "--output"}, description = "The output file")
	File outputFile;
	
	@Option(names = "--format", description = "The output format: "
			+ "json or avro ")
	Outputformat format;
	
	@Option(names = "--list", showDefaultValue = Visibility.ALWAYS, description = "Don't include catalogs metadata information, just list existing catalogs")
	Boolean listOnly = false;
	
	@Option(names = "--schema", description = "Prints the avro schema used by this tool and exits")
	Boolean schema;
	    
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE, false);
	}
	
	private enum Outputformat {
		csv, json, avro
	}
	
	static class LevelTypeConverter implements ITypeConverter<Level> {

		@Override
		public Level convert(String value) throws Exception {
			return Level.parse(value);
		}
	}
	
	private static void printSchema() {
		try {
			URL url = Resources.getResource("avro/JdbcDatasource.avsc");
			String text = Resources.toString(url, Charsets.UTF_8);
			System.out.println(text);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		for(String arg : args) {
			if("--schema".equals(arg)) {
				printSchema();
				System.exit(0);
			}
		}
		
//		new CommandLine(new Main()).
//			addSubcommand("schema", new SchemaCmd()).
//			parseWithHandler(new RunAll(), System.err, args);
		//CommandLine.call(new Main(), args);
		new CommandLine(new Main()).execute(args);

//		Main main = new Main();
//		new CommandLine(main).parse(args);
//		System.out.println(main);
	}

	// https://stackoverflow.com/questions/6307648/change-global-setting-for-logger-instances
	private void changeAppLogLevel(Level logLevel) {
		Logger rootLogger = LogManager.getLogManager().getLogger("");
		rootLogger.setLevel(logLevel);
		for (Handler h : rootLogger.getHandlers()) {
		    h.setLevel(logLevel);
		}
	}
	
	@Override
	public Void call() throws Exception {
		changeAppLogLevel(this.logLevel);
		
		
		// Outputs - Console and file if setted
		Set<OutputStream> outputs = new HashSet<OutputStream>();
		//outputs.add(System.out);
		if(outputFile!=null) {
			outputs.add(new FileOutputStream(outputFile));
		}else {
			outputs.add(System.out);
		}
		
		// Output formats
		List<CrawlObserverI> observers = new LinkedList<CrawlObserverI>();
		if(format ==null) format = Outputformat.json;
		switch (format) {
		case json:
			for(OutputStream out : outputs) {
				observers.add(new JsonOutputObserverImpl(out));
			}
			if(outputFile!=null) {
				observers.add(new SimpleCrawlObserverImpl());
			}
			
			break;
		case avro:
			if (outputFile==null) {
				throw new ParameterException(new CommandLine(this), "Avro format requires a valid output file");
			}
			observers.add(new AvroOutputObserverImpl(outputFile));
			observers.add(new SimpleCrawlObserverImpl());
			//observers.add(new JsonOutputObserverImpl(System.out));
			break;
		default:
			break;
		}
		
		password = StringUtils.isEmpty(password) ? System.getenv(passwordEnvironmentVariable) : password;
		
		if(StringUtils.isEmpty(password)) {
			password = "";
			//throw new ParameterException(new CommandLine(this), "Inform password via parameter or via environment variable " + passwordEnvironmentVariable);
		}
		
		MetadataCrawlerConfig config = new MetadataCrawlerConfig.Builder(jdbcUrl)
				.user(user)
				.password(password)
				.catalogs_regex(catalogs_regex)
				.onlyDefault(onlyDefault)
				.build();
		
		// Crawl catalogs
		MetadataCrawler crawler = new MetadataCrawler(config);
		crawler.crawl(observers, !listOnly);
		
		return null;
	}
}
