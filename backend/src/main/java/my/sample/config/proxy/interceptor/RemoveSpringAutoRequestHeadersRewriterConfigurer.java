package my.sample.config.proxy.interceptor;

import com.github.mkopylec.charon.forwarding.interceptors.RequestForwardingInterceptorConfigurer;

public class RemoveSpringAutoRequestHeadersRewriterConfigurer extends RequestForwardingInterceptorConfigurer<RemoveSpringAutoRequestHeadersRewriter> {

    private RemoveSpringAutoRequestHeadersRewriterConfigurer() {
        super( new RemoveSpringAutoRequestHeadersRewriter() );
    }

    public static RemoveSpringAutoRequestHeadersRewriterConfigurer removingRequestHeadersInterceptor() {
        return new RemoveSpringAutoRequestHeadersRewriterConfigurer();
    }
}
