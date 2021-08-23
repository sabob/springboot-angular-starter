package my.sample.config.proxy.interceptor.hateoas;

import com.github.mkopylec.charon.forwarding.interceptors.RequestForwardingInterceptorConfigurer;

public class HateoasStripperInterceptorConfigurer extends RequestForwardingInterceptorConfigurer<HateoasStripperInterceptor> {

    private HateoasStripperInterceptorConfigurer() {
        super( new HateoasStripperInterceptor() );
    }

    public static HateoasStripperInterceptorConfigurer hateoasStripperInterceptor() {
        return new HateoasStripperInterceptorConfigurer();
    }
}
