package my.sample.client.external;

import my.sample.client.external.deps.ExternalServiceClientDeps;
import my.sample.client.external.representation.ExternalTO;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExternalServiceClient {

    private static final Logger LOGGER = Logger.getLogger( ExternalServiceClient.class.getName() );

    private WebTarget externalServiceTarget;

    private WebTarget branchTarget;

    private ExternalServiceClientDeps deps;

    public ExternalServiceClient( ExternalServiceClientDeps deps ) {
        this.deps = deps;
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature httpAuthenticationFeature = HttpAuthenticationFeature.basic( deps.getUsername(), deps.getPassword() );

        externalServiceTarget = client.target( deps.getBankUrl() );
        externalServiceTarget.register( httpAuthenticationFeature );
    }

    public List<ExternalTO> getExternalStuff() {
        Response response = externalServiceTarget
                .request( MediaType.APPLICATION_JSON_TYPE ).get();

        if ( response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL ) {
            List<ExternalTO> result = response.readEntity( new GenericType<List<ExternalTO>>() {

            } );

            return result;

        } else {

            String errorDescription = response.readEntity( String.class );
            LOGGER.log( Level.SEVERE, "There is a problem. Could not retrieve banks" +
                    ", response: " + response.getStatus() + " " + errorDescription );
            throw new RuntimeException( errorDescription );
        }
    }
}
