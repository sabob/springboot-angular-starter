package my.sample.config.proxy.interceptor.bearer;

import com.github.mkopylec.charon.forwarding.interceptors.*;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import my.sample.config.proxy.InterceptorTypes;
import my.sample.config.proxy.interceptor.base.AbstractRequestHeadersRewriter;

import java.net.URI;
import java.util.function.Consumer;

import static org.slf4j.LoggerFactory.getLogger;

class BearerAuthenticationInterceptor extends AbstractRequestHeadersRewriter implements RequestForwardingInterceptor {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    @Override
    public HttpResponse forward( HttpRequest request, HttpRequestExecution execution ) {

        try {

            logStart( execution.getMappingName() );

            addBearerToken( request.getHeaders(), request.getURI(), request::setHeaders );
            HttpResponse response = execution.execute( request );
            logEnd( execution.getMappingName() );

            return response;

        } catch ( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }

    void addBearerToken( HttpHeaders headers, URI uri, Consumer<HttpHeaders> headersSetter ) {
        headers.setBearerAuth( getToken() );
    }

    @Override
    public RequestForwardingInterceptorType getType() {
        return InterceptorTypes.BEARER_INTERCEPTOR_TYPE;
    }
}
