package my.sample.config.proxy.interceptor.basicauth;

import com.github.mkopylec.charon.forwarding.interceptors.*;
import org.springframework.http.HttpHeaders;
import my.sample.config.proxy.InterceptorTypes;
import my.sample.config.proxy.interceptor.base.AbstractRequestHeadersRewriter;

import java.net.URI;
import java.util.function.Consumer;

class BasicAuthenticationInterceptor extends AbstractRequestHeadersRewriter implements RequestForwardingInterceptor {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    @Override
    public HttpResponse forward( HttpRequest request, HttpRequestExecution execution ) {

        try {

            logStart( execution.getMappingName() );

            addBasicAuth( request.getHeaders(), request.getURI(), request::setHeaders );
            HttpResponse response = execution.execute( request );
            //String body = IOUtils.toString( response.getBody(), StandardCharsets.UTF_8.name() );
            logEnd( execution.getMappingName() );

            return response;

        } catch ( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }

    void addBasicAuth( HttpHeaders headers, URI uri, Consumer<HttpHeaders> headersSetter ) {

//        HttpHeaders rewrittenHeaders = copyHeaders( headers );
//        rewrittenHeaders.setBasicAuth( getUsername(), getPassword() );
//        headersSetter.accept( rewrittenHeaders );
        headers.setBasicAuth( getUsername(), getPassword() );
        //LOGGER.trace( "Added basic auth header, username: {}, password: {}", getUsername(), getPassword() );

        logger.trace( "Added basic auth header, username: {}, password: {}", getUsername(), getPassword() );
    }

    @Override
    public RequestForwardingInterceptorType getType() {
        return InterceptorTypes.BASIC_AUTH_INTERCEPTOR_TYPE;
    }

    @Override
    public void logStart( String mappingName ) {
        logger.debug( "[Start] Request 'Host' BasicAuth rewriting for '{}' request mapping", mappingName );
    }

    @Override
    public void logEnd( String mappingName ) {
        logger.debug( "[End] Request 'Host' BasicAuth rewriting for '{}' request mapping", mappingName );
    }
}
