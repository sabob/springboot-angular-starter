package my.sample.api.resource;

import my.sample.client.external.ExternalServiceClient;
import my.sample.client.external.representation.ExternalTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Component
@Path( "/external" )
@Produces( "application/json" )
@Consumes( "application/json" )
public class ExternalResource {

    @Autowired
    ExternalServiceClient externalServiceClient;

    @GET
    @Path( "/" )
    public List<ExternalTO> getById( @Context UriInfo uriInfo ) {

        List<ExternalTO> toList = externalServiceClient.getExternalStuff();

        return toList;
    }
}
