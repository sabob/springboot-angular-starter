package my.sample.config.proxy;

import com.github.mkopylec.charon.configuration.CharonConfigurer;
import com.github.mkopylec.charon.forwarding.RestTemplateConfigurer;
import com.github.mkopylec.charon.forwarding.interceptors.RequestForwardingInterceptorType;
import com.github.mkopylec.charon.forwarding.interceptors.rewrite.RegexRequestPathRewriterConfigurer;
import com.github.mkopylec.charon.forwarding.interceptors.rewrite.RequestHostHeaderRewriterConfigurer;
import my.sample.config.FilterOrder;
import my.sample.config.proxy.interceptor.RemoveSpringAutoRequestHeadersRewriterConfigurer;
import my.sample.config.proxy.interceptor.basicauth.BasicAuthenticationInterceptorConfigurer;
import my.sample.config.proxy.interceptor.hateoas.HateoasStripperInterceptorConfigurer;
import my.sample.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.Duration;

import static com.github.mkopylec.charon.configuration.CharonConfigurer.charonConfiguration;
import static com.github.mkopylec.charon.configuration.RequestMappingConfigurer.requestMapping;
import static com.github.mkopylec.charon.forwarding.TimeoutConfigurer.timeout;
import static com.github.mkopylec.charon.forwarding.interceptors.rewrite.RequestServerNameRewriterConfigurer.requestServerNameRewriter;

@Configuration
public class CharonConfiguration {

    @Value( "${charon.passthrough.service.basic.auth.username}" )
    private String passthroughAppBasicAuthUsername;

    @Value( "${charon.passthrough.service.basic.auth.password}" )
    private String passthroughAppBasicAuthPassword;

    // Definition mapping
    public static String PASSTHROUGH_SERVICE_NAME;

    @Value( "${charon.passthrough.service.name}" )
    private String passthroughServiceName;

    @Value( "${charon.passthrough.service.host}" )
    private String passthroughAppHost;

    @Value( "${charon.passthrough.service.context-path}" )
    private String passthroughAppContextPath;

    private String passthroughAppPathRegex;

    @Value( "${server.servlet.context-path}" )
    private String contextPath;

    @PostConstruct
    public void init() {
        contextPath = StringUtils.prependIfMissing( contextPath, "/" );

        PASSTHROUGH_SERVICE_NAME = passthroughServiceName;
        passthroughAppContextPath = StringUtils.prependIfMissing( passthroughAppContextPath, "/" );
        passthroughAppPathRegex = contextPath + passthroughAppContextPath + "/.*";
    }

    @Bean
    public CharonConfigurer charonConfigurer() {

        CharonConfigurer configurer = charonConfiguration()
                .unset( RequestForwardingInterceptorType.RESPONSE_PROTOCOL_HEADERS_REWRITER )
                .unset( RequestForwardingInterceptorType.RESPONSE_COOKIE_REWRITER )
                .unset( RequestForwardingInterceptorType.REQUEST_PROTOCOL_HEADERS_REWRITER )
                .unset( RequestForwardingInterceptorType.REQUEST_PROXY_HEADERS_REWRITER )

                .set( RestTemplateConfigurer.restTemplate()
                                            .set( timeout()
                                                    .connection( Duration.ofSeconds( Constants.CONNECT_TIMEOUT_IN_SECONDS ) )
                                                    .read( Duration.ofMinutes( Constants.READ_TIMEOUT_IN_MINUTES ) ) )
                )

                .filterOrder( FilterOrder.CHARON_PROXY )
                .add( requestMapping( PASSTHROUGH_SERVICE_NAME )

                        .set( BasicAuthenticationInterceptorConfigurer.basicAuthInterceptor()
                                                                      .username( passthroughAppBasicAuthUsername )
                                                                      .password( passthroughAppBasicAuthPassword ) )
                        .set( RemoveSpringAutoRequestHeadersRewriterConfigurer.removingRequestHeadersInterceptor() )
                        .set( HateoasStripperInterceptorConfigurer.hateoasStripperInterceptor() )
                        .set( RequestHostHeaderRewriterConfigurer.requestHostHeaderRewriter() )

                        .set( requestServerNameRewriter().outgoingServers( passthroughAppHost ) )

                        .pathRegex( passthroughAppPathRegex )
                        .set( RegexRequestPathRewriterConfigurer.regexRequestPathRewriter().paths( contextPath + "/(?<path>.*)", "/<path>" ) )
                );

        return configurer;

    }
}
