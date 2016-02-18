package com.trubeacon.cloverandroidapi.client.json;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = -6187275316695171136L;
    
    private static final String NAME = "Payeezy Module";

    private static JacksonObjectMapper objectMapper = null;
    public static JacksonObjectMapper getObjectMapper() {
    	if (objectMapper == null) {
    		objectMapper = new JacksonObjectMapper();
    	}
    	return objectMapper;
    }
    
	public JacksonObjectMapper() {

		super();
		super.setSerializationInclusion(Include.NON_NULL);
		super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		super.setVisibility(PropertyAccessor.ALL, Visibility.NONE);				
		
		// custom (de)serializers for specific classes
		SimpleModule module = new SimpleModule(NAME, new Version(1, 0, 0, null, null, null));
		super.registerModule(module);
		
	}
	
}
