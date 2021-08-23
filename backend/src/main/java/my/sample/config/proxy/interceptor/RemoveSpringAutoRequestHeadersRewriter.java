package my.sample.config.proxy.interceptor;

import com.github.mkopylec.charon.forwarding.interceptors.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import my.sample.config.proxy.InterceptorTypes;
import my.sample.config.proxy.interceptor.base.AbstractRequestHeadersRewriter;

import java.util.ArrayList;
import java.util.List;

public class RemoveSpringAutoRequestHeadersRewriter extends AbstractRequestHeadersRewriter implements RequestForwardingInterceptor {

    public RemoveSpringAutoRequestHeadersRewriter() {
    }

    public RequestForwardingInterceptorType getType() {
        return InterceptorTypes.REMOVE_SPRING_AUTO_REQUEST_HEADER_REWRITER;
    }

    @Override
    public HttpResponse forward( HttpRequest request, HttpRequestExecution execution ) {

        logStart( execution.getMappingName() );

        List<String> removeHeaders = new ArrayList();

        // Charon V4.9 (at least sets an empty body to an empty array which makes Spring add ContentType and Content Length headers)
        // https://github.com/mkopylec/ch aron-spring-boot-starter/issues/126
        if ( hasNoBodyRequest( request ) ) {
            removeHeaders.add( HttpHeaders.CONTENT_TYPE );
            removeHeaders.add( HttpHeaders.CONTENT_LENGTH );
        }

        removeHeaders( removeHeaders, request.getHeaders(), request::setHeaders );

        HttpResponse response = execution.execute( request );

        logEnd( execution.getMappingName() );
        return response;
    }

    protected boolean hasNoBodyRequest( org.springframework.http.HttpRequest request ) {
        if ( request.getMethod() == HttpMethod.GET ||
                request.getMethod() == HttpMethod.DELETE ||
                request.getMethod() == HttpMethod.HEAD ||
                request.getMethod() == HttpMethod.TRACE ||
                request.getMethod() == HttpMethod.OPTIONS ) {
            return true;
        }
        return false;
    }
}
