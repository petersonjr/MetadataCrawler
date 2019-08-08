package io.github.petersonjr.metadatacrawler.observer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

import io.github.petersonjr.metadatacrawler.model.JdbcDatasource;
import io.github.petersonjr.metadatacrawler.util.AvroJsonConverter;



// Important:
// https://stackoverflow.com/questions/5543490/json-naming-convention
	
public class JsonLinesOutputObserverImpl implements CrawlObserverI {

	private AvroJsonConverter<JdbcDatasource> jsonConv = 
			new AvroJsonConverter<>(JdbcDatasource.getClassSchema(), JdbcDatasource.class);
	private JsonGenerator generator;
	
	public JsonLinesOutputObserverImpl(OutputStream out) {
		// https://stackoverflow.com/questions/23007567/java-json-pretty-print-javax-json
		Map<String, Object> properties = new HashMap<String, Object>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        
		JsonGeneratorFactory jf = Json.createGeneratorFactory(properties);
		generator = jf.createGenerator(new OutputStreamWriter(out, StandardCharsets.UTF_8));
		//generator = jf.createGenerator(out, Charset.forName("UTF-8"));
	}
	
	@Override
	public void observe(JdbcDatasource catalog) {
		try {
			String json = jsonConv.encodeToJson(catalog, false);
			JsonReader jsonReader = Json.createReader(new StringReader(json));
			JsonObject object = jsonReader.readObject();
			jsonReader.close();
			generator.write(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		//generator.write(new JSo)
		//generator.writeStartObject();
		
		generator.flush();
		
	}

	@Override
	public void crawlEnded() {
		generator.writeEnd();
		generator.flush();
		//generator.close();
	}

	@Override
	public void crawlStart() {
		generator.writeStartArray();
	}

}
