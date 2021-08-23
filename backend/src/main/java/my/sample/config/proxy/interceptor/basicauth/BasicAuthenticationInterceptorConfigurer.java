package my.sample.config.proxy.interceptor.basicauth;

import com.github.mkopylec.charon.forwarding.interceptors.RequestForwardingInterceptorConfigurer;

public class BasicAuthenticationInterceptorConfigurer extends RequestForwardingInterceptorConfigurer<BasicAuthenticationInterceptor> {

    private BasicAuthenticationInterceptorConfigurer() {
        super( new BasicAuthenticationInterceptor() );
    }

    public static BasicAuthenticationInterceptorConfigurer basicAuthInterceptor() {
        return new BasicAuthenticationInterceptorConfigurer();
    }

    public BasicAuthenticationInterceptorConfigurer username( String username ) {
        configuredObject.setUsername( username );
        return this;
    }

    public BasicAuthenticationInterceptorConfigurer password( String password ) {
        configuredObject.setPassword( password );
        return this;
    }
}
