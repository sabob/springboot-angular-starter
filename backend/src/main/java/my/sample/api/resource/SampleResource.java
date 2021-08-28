package my.sample.api.resource;

import my.sample.api.resource.assembler.SampleAssembler;
import my.sample.api.resource.representation.PageTO;
import my.sample.api.resource.representation.SampleTO;
import my.sample.domain.entity.Sample;
import my.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Optional;

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
    @Path( "/{id}" )
    public Response getById( @PathParam( "id" ) Long idParam, @Context UriInfo uriInfo ) {

        Optional<Sample> optional = sampleService.getSample( idParam );

        if (optional.isPresent()) {
            SampleTO to = SampleAssembler.toRepresentation( optional.get() );
            return Response.ok( to ).build();
        }

        return Response
                .status( Response.Status.NOT_FOUND )
                .type( "application/problem+json" )
                .entity( Problem.create()
                                .withTitle( "The Sample was not found." )
                                .withType( URI.create( "http://my.foo.com/sample/problems/sample-not-found" ) )
                                .withInstance( uriInfo.getRequestUri() )
                                .withStatus( HttpStatus.NOT_FOUND )
                                .withDetail( String.format( "The claim role %s was not found.", idParam ) ))
                .build();
    }

    @GET
    @Path( "/" )
    public PageTO<SampleTO> getSamples( @QueryParam( "page" ) int page,
                                        @QueryParam( "pageSize" ) @DefaultValue( "20" ) int pageSize,
                                        @QueryParam( "query" ) @DefaultValue( "" ) String code ) {

        Page<Sample> result = sampleService.getSamples( code, page, pageSize );
        PageTO<SampleTO> toList = SampleAssembler.toRepresentation( result );
        return toList;
    }

    @POST
    @Path( "/" )
    public SampleTO create( SampleTO to ) {
        Sample sample = SampleAssembler.toDomain( to );
        sample = sampleService.save( sample );

        to = SampleAssembler.toRepresentation( sample );
        return to;
    }

    @PUT
    @Path( "/" )
    public SampleTO update( SampleTO to ) {
        Sample sample = SampleAssembler.toDomain( to );
        sample = sampleService.save( sample );

        to = SampleAssembler.toRepresentation( sample );
        return to;
    }

    @GET
    @Path( "/profile" )
    public String[] profile() {
        return environment.getActiveProfiles();
    }
}
