package io.github.petersonjr.metadatacrawler.observer;


import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import io.github.petersonjr.metadatacrawler.model.JdbcDatasource;



// Important:
// https://stackoverflow.com/questions/5543490/json-naming-convention
	
public class AvroOutputObserverImpl implements CrawlObserverI {

	final DatumWriter<JdbcDatasource> datumWriter = new SpecificDatumWriter<>(JdbcDatasource.class);
	final DataFileWriter<JdbcDatasource> dataFileWriter = new DataFileWriter<>(datumWriter);
	
	public AvroOutputObserverImpl(File output) {
		try {
			dataFileWriter.create(JdbcDatasource.SCHEMA$, output);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void observe(JdbcDatasource catalog) {
		try {
            dataFileWriter.append(catalog);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
	}

	@Override
	public void crawlEnded() {
		try {
			dataFileWriter.flush();
			dataFileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void crawlStart() {
		
	}

}
