package my.sample.config.proxy.interceptor.base;

import com.github.mkopylec.charon.configuration.Valid;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.github.mkopylec.charon.forwarding.Utils.copyHeaders;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractRequestHeadersRewriter implements Valid {

    protected final Logger logger = getLogger( getClass() );

    public AbstractRequestHeadersRewriter() {
    }

    public void addHeaders( HttpHeaders headers, Map<String, List<String>> newHeaders, Consumer<HttpHeaders> headersSetter ) {
        HttpHeaders rewrittenHeaders = copyHeaders( headers );

        for ( String key : newHeaders.keySet() ) {
            List<String> values = newHeaders.get( key );

            for ( String value : values ) {
                rewrittenHeaders.add( key, value );
            }
        }
        headersSetter.accept( rewrittenHeaders );
        logger.debug( "Request headers rewritten from {} to {}", headers, rewrittenHeaders );
    }

    public void setHeaders( HttpHeaders headers, Map<String, String> newHeaders, Consumer<HttpHeaders> headersSetter ) {
        HttpHeaders rewrittenHeaders = copyHeaders( headers );

        for ( String key : newHeaders.keySet() ) {
            String value = newHeaders.get( key );
            rewrittenHeaders.set( key, value );
        }
        headersSetter.accept( rewrittenHeaders );
        logger.debug( "Request headers rewritten from {} to {}", headers, rewrittenHeaders );
    }

    public void removeHeaders( List<String> removeHeaders, HttpHeaders headers, Consumer<HttpHeaders> headersSetter ) {
        HttpHeaders rewrittenHeaders = copyHeaders( headers );

        for ( String remove : removeHeaders ) {
            rewrittenHeaders.remove( remove );
        }

        headersSetter.accept( rewrittenHeaders );
        logger.debug( "Response headers rewritten from {} to {}", headers, rewrittenHeaders );
    }

    public void logStart( String mappingName ) {
        logger.trace( "[Start] Response protocol headers rewriting for '{}' request mapping", mappingName );
    }

    public void logEnd( String mappingName ) {
        logger.trace( "[End] Response protocol headers rewriting for '{}' request mapping", mappingName );
    }
}
