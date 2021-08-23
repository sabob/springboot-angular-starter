package my.sample.config.proxy.interceptor.hateoas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class HateoasUtil {

    private static final Logger LOGGER = getLogger( HateoasUtil.class );

    public static String rewriteExternalService( String value ) {

        // Below is code on how to potentially rewrite the body of a response if needed eg remove hateoas :)
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode node = mapper.readTree( value );
//            JsonNode embedded = node.get( "_embedded" );
//            JsonNode summaries = node.get( "personSummaries" );
//
//            String result = mapper.writeValueAsString( summaries );
//            return result;
//
//        } catch ( Exception ex ) {
//            LOGGER.error( ex.getMessage(), ex );
//        }

        return value;
    }

}
