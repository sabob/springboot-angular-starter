package my.sample.config.proxy.interceptor;

import com.github.mkopylec.charon.forwarding.interceptors.RequestForwardingInterceptorConfigurer;

public class RemoveGzipRequestHeaderConfigurer extends RequestForwardingInterceptorConfigurer<RemoveGzipRequestHeader> {

    private RemoveGzipRequestHeaderConfigurer() {
        super( new RemoveGzipRequestHeader() );
    }

    public static RemoveGzipRequestHeaderConfigurer removingRequestHeadersInterceptor() {
        return new RemoveGzipRequestHeaderConfigurer();
    }
}
