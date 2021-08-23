package my.sample.config.proxy.interceptor.bearer;

import com.github.mkopylec.charon.forwarding.interceptors.RequestForwardingInterceptorConfigurer;

public class BearerAuthenticationInterceptorConfigurer extends RequestForwardingInterceptorConfigurer<BearerAuthenticationInterceptor> {

    private BearerAuthenticationInterceptorConfigurer() {
        super( new BearerAuthenticationInterceptor() );
    }

    public static BearerAuthenticationInterceptorConfigurer bearerAuthInterceptor() {
        return new BearerAuthenticationInterceptorConfigurer();
    }

    public BearerAuthenticationInterceptorConfigurer token( String token ) {
        configuredObject.setToken( token );
        return this;
    }
}
