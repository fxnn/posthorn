package de.fxnn.posthorn.technical;

import java.io.IOException;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
 
/**
 * A simple Java 8 centric Module for Jackon. Allows to do things like: 
 * 
 * ObjectMapper jacksonMapper = new ObjectMapper();
 * Jackson8Module module = new Jackson8Module();
 * module.addStringSerializer(LocalDate.class, (val) -> val.toString());
 * module.addStringSerializer(LocalDateTime.class, (val) -> val.toString());
 * jacksonMapper.registerModule(module);
 * 
 * // below typical best practice in some cases
 * jacksonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
 *
 * from https://gist.github.com/jeremychone/a7e06b8baffef88a8816
 **/
public class Jackson8Module extends SimpleModule{
 
	public <T> void addCustomSerializer(Class<T> cls, SerializeFunction<T> serializeFunction){
		JsonSerializer<T> jsonSerializer = new JsonSerializer<T>(){
			@Override
			public void serialize(T t, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
				serializeFunction.serialize(t, jgen);
			}
		};
		addSerializer(cls,jsonSerializer);
	}
 
	public <T> void addStringSerializer(Class<T> cls, Function<T,String> serializeFunction){
		JsonSerializer<T> jsonSerializer = new JsonSerializer<T>(){
			@Override
			public void serialize(T t, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
				String val = serializeFunction.apply(t);
				jgen.writeString(val);
			}
		};
		addSerializer(cls,jsonSerializer);
	}
 
	public static interface SerializeFunction<T>{
		public void serialize(T t, JsonGenerator jgen) throws IOException, JsonProcessingException;
	}
}