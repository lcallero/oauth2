package br.com.oauthtwo.setup;
 
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson JSON processor could be controlled via providing a custom Jackson ObjectMapper instance. 
 * This could be handy if you need to redefine the default Jackson behavior and to fine-tune how 
 * your JSON data structures look like (copied from Jersey web site). * 
 * @see https://jersey.java.net/documentation/latest/media.html#d0e4799
 */

@Provider
public class JacksonJsonProvider implements ContextResolver<ObjectMapper> {
	
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    static {
      MAPPER.setSerializationInclusion(Include.NON_EMPTY);
      MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
      //MAPPER.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }
 
    public JacksonJsonProvider() {
        System.out.println("Instantiate MyJacksonJsonProvider");
    }
     
    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("MyJacksonProvider.getContext() called with type: "+type);
        return MAPPER;
    } 
}