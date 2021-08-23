package my.sample.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import io.micrometer.core.instrument.util.JsonUtils;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.util.TimeZone;

/**
 *
 */
@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public JacksonConfig() {
        mapper = createMapperWithUTCDate();
        mapper.enable( SerializationFeature.INDENT_OUTPUT );
        mapper.configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );
        mapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public ObjectMapper getContext( Class<?> type ) {
        return mapper;
    }

    public ObjectMapper createMapperWithUTCDate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new StdDateFormat());
        TimeZone timezone = TimeZone.getDefault();
        mapper.setTimeZone(timezone);
        return mapper;
    }
}
