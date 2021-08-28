package my.sample.config.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.sample.api.resource.*;
import my.sample.api.resource.exception.GenericExceptionMapper;
import my.sample.config.JacksonConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Component
@ApplicationPath( "/api" )
public class JerseyConfig extends ResourceConfig {

    @Autowired
    public JerseyConfig( ObjectMapper objectMapper ) {

        register( SampleResource.class );
        register( GenericExceptionMapper.class );
        register( JacksonConfig.class );

        // register jackson for json
        register( new ObjectMapperContextResolver( objectMapper ) );
    }

    @Provider
    public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        private final ObjectMapper mapper;

        public ObjectMapperContextResolver( ObjectMapper mapper ) {
            this.mapper = mapper;
        }

        @Override
        public ObjectMapper getContext( Class<?> aClass ) {
            return mapper;
        }
    }
}
