package my.sample.config.proxy.interceptor;

import com.github.mkopylec.charon.forwarding.interceptors.*;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import my.sample.config.proxy.InterceptorTypes;
import my.sample.config.proxy.interceptor.base.AbstractRequestHeadersRewriter;

import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class RemoveGzipRequestHeader extends AbstractRequestHeadersRewriter implements RequestForwardingInterceptor {

    protected static final Logger LOGGER = getLogger( RemoveGzipRequestHeader.class );

    public RemoveGzipRequestHeader() {
    }

    public RequestForwardingInterceptorType getType() {
        return InterceptorTypes.REMOVE_GZIP_REQUEST_HEADER_REWRITER;
    }

    @Override
    public HttpResponse forward( HttpRequest request, HttpRequestExecution execution ) {

        logStart( execution.getMappingName() );

        List<String> removeHeaders = Arrays.asList(
                HttpHeaders.ACCEPT_ENCODING
        );

        removeHeaders( removeHeaders, request.getHeaders(), request::setHeaders );

        HttpResponse response = execution.execute( request );


        logEnd( execution.getMappingName() );
        return response;
    }

    public void logStart( String mappingName ) {
        LOGGER.debug( "[Start] Response protocol headers rewriting for '{}' request mapping", mappingName );
    }

    public void logEnd( String mappingName ) {
        LOGGER.debug( "[End] Response protocol headers rewriting for '{}' request mapping", mappingName );
    }
}
