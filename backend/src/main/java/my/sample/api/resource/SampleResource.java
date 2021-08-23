package my.sample.api.resource;

import my.sample.api.resource.representation.SampleTO;
import my.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@Component
@Path( "/sample" )
@Produces( "application/json" )
@Consumes( "application/json" )
public class SampleResource {

    @Autowired
    private Environment environment;

    @Autowired
    SampleService sampleService;

    @GET
    @Path( "/" )
    public List<SampleTO> getStuff( @QueryParam( "page" ) int page,
                                      @QueryParam( "pageSize" ) @DefaultValue( "20" ) int pageSize,
                                      @QueryParam( "query" ) @DefaultValue( "" ) String query ) {

        List<SampleTO> result = sampleService.getStuff( query, page, pageSize );
        return result;
    }

    @GET
    @Path( "/profile" )
    public String[] profile() {
        return environment.getActiveProfiles();
    }
}
