package my.sample.config;

import my.sample.client.external.ExternalServiceClient;
import my.sample.client.external.deps.ExternalServiceClientDeps;
import my.sample.service.SampleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class ServiceConfig {

    private static final Logger LOGGER = Logger.getLogger( ServiceConfig.class.getName() );

    @Autowired
    private ApplicationContext applicationContext;

    @Value( "${external.service.basic.auth.username}" )
    private String externalAppBasicAuthUsername;

    @Value( "${external.service.basic.auth.password}" )
    private String eternalAppBasicAuthPassword;

    @Value( "${external.service.context-path}" )
    private String externalServiceContextPath;

    @Value( "${external.service.host}" )
    private String externalServiceHost;

    @Value( "${external.service.api.path}" )
    private String externalServiceApiPath;

    @Bean
    public SampleService getSampleService( ) {

        SampleService service = new SampleService( );

        return service;
    }

    @Bean
    public ExternalServiceClient getExternalServiceClient() {

        externalServiceContextPath = StringUtils.prependIfMissing( externalServiceContextPath, "/" );
        String externalServiceUrl = externalServiceHost + externalServiceContextPath + externalServiceApiPath;

        ExternalServiceClientDeps deps = new ExternalServiceClientDeps();
        deps.setUsername( externalAppBasicAuthUsername );
        deps.setPassword( eternalAppBasicAuthPassword );
        deps.setServiceUrl( externalServiceUrl );

        ExternalServiceClient client = new ExternalServiceClient( deps );

        return client;
    }
}
