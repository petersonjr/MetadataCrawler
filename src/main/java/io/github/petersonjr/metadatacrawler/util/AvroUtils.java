package io.github.petersonjr.metadatacrawler.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;

public class AvroUtils {

	public static String avroToJson(byte[] avro) throws IOException {
	    boolean pretty = false;
	    GenericDatumReader<GenericRecord> reader = null;
	    JsonEncoder encoder = null;
	    ByteArrayOutputStream output = null;
	    DataFileStream<GenericRecord> streamReader = null;
	    try {
	        reader = new GenericDatumReader<GenericRecord>();
	        InputStream input = new ByteArrayInputStream(avro);
	        streamReader = new DataFileStream<GenericRecord>(input, reader);
	        output = new ByteArrayOutputStream();
	        Schema schema = streamReader.getSchema();
	        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
	        encoder = EncoderFactory.get().jsonEncoder(schema, output, pretty);
	        for (GenericRecord datum : streamReader) {
	            writer.write(datum, encoder);
	        }
	        encoder.flush();
	        output.flush();
	        
	        return new String(output.toByteArray());
	    } finally {
	    	try { 
	        	if (output != null) output.close();
	        	streamReader.close();
	        	
	        } catch (Exception e) { }
	    }
	}
}
