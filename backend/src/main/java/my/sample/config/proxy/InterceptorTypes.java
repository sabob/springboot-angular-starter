package my.sample.config.proxy;

import com.github.mkopylec.charon.forwarding.interceptors.RequestForwardingInterceptorType;

public class InterceptorTypes {

    public static final RequestForwardingInterceptorType REMOVE_SPRING_AUTO_REQUEST_HEADER_REWRITER = new RequestForwardingInterceptorType(999);

    public static final RequestForwardingInterceptorType BASIC_AUTH_INTERCEPTOR_TYPE = new RequestForwardingInterceptorType( 10 );

    public static final RequestForwardingInterceptorType BEARER_INTERCEPTOR_TYPE = new RequestForwardingInterceptorType( 11 );

    //public static final RequestForwardingInterceptorType REMOVE_SPRING_AUTO_REQUEST_HEADER_REWRITER = new RequestForwardingInterceptorType(999);

    public static final RequestForwardingInterceptorType REMOVE_GZIP_REQUEST_HEADER_REWRITER = new RequestForwardingInterceptorType(702);

    public static final RequestForwardingInterceptorType HATEOAS_INTERCEPTOR_TYPE = new RequestForwardingInterceptorType( 670 );

    public static final RequestForwardingInterceptorType REMOVING_RESPONSE_HEADER_REWRITER = new RequestForwardingInterceptorType(901);


}
