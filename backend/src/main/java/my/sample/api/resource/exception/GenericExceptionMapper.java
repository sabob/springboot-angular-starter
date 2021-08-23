package my.sample.api.resource.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import my.sample.util.Feedback;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = LoggerFactory.getLogger( GenericExceptionMapper.class );

    @Context
    private Request request;

    private Variant getVariant( Request request ) {
        if ( request == null ) return null;
        final List<Variant> variants = Variant.mediaTypes( MediaType.APPLICATION_JSON_TYPE ).build();
        final Variant variant = request.selectVariant( variants );
        return variant;
    }

    @Override
    public Response toResponse( Throwable ex ) {

        LOGGER.error( ex.getMessage(), ex );

        Feedback feedback = new Feedback();
        feedback.setFeedbackMessage( ex.getMessage() );

        if ( ex instanceof WebApplicationException ) {
            WebApplicationException was = ( WebApplicationException ) ex;
            feedback.setCode( was.getResponse().getStatus() );
        } else {
            feedback.setCode( HttpStatus.INTERNAL_SERVER_ERROR.value() );
        }

        Response.ResponseBuilder builder = Response.status( HttpStatus.INTERNAL_SERVER_ERROR.value() ).entity( feedback );
        builder.type( MediaType.APPLICATION_JSON );

        return builder.build();
    }

}
