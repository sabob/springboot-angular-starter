package my.sample.config.proxy.interceptor.hateoas;

import com.github.mkopylec.charon.forwarding.interceptors.*;
import org.slf4j.Logger;
import my.sample.config.proxy.CharonConfiguration;
import my.sample.config.proxy.InterceptorTypes;

import java.nio.charset.StandardCharsets;

import static org.slf4j.LoggerFactory.getLogger;

class HateoasStripperInterceptor implements RequestForwardingInterceptor {

    private static final Logger LOGGER = getLogger( HateoasStripperInterceptor.class );

    @Override
    public HttpResponse forward( HttpRequest request, HttpRequestExecution execution ) {

        logStart( execution.getMappingName() );
        HttpResponse response = execution.execute( request );

        try {

            // Strip hateoas from Party API
            if ( CharonConfiguration.EXTERNAL_SERVICE_NAME.equals( execution.getMappingName() ) ) {
                rewriteExternalServiceApiBody( execution, response );
            }

            // Add more strip logic here ;-)

        } catch ( Exception ex ) {
            LOGGER.error( ex.getMessage(), ex );
        }

        logEnd( execution.getMappingName() );

        return response;
    }

    public void rewriteExternalServiceApiBody( HttpRequestExecution execution, HttpResponse response ) {

        String origBody = new String( response.getBodyAsBytes(), StandardCharsets.UTF_8 );
        String body = HateoasUtil.rewriteExternalService( origBody );
        if ( body.isEmpty() ) {
            body = "[]";
        }

        response.setBody( body.getBytes() );

    }

    @Override
    public RequestForwardingInterceptorType getType() {
        return InterceptorTypes.HATEOAS_INTERCEPTOR_TYPE;
    }

    void logStart( String mappingName ) {
        LOGGER.debug( "[Start] Response body rewritten for '{}' request mapping", mappingName );
    }

    void logEnd( String mappingName ) {
        LOGGER.debug( "[End] Response body rewritten for '{}' request mapping", mappingName );
    }
}
