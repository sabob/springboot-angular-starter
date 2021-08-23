package my.sample.config.jersey;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class BearerTokenFeature implements ClientRequestFilter {

    public static final String FILTER_HEADER_KEY = "Authorization";

    private String token;

    public BearerTokenFeature( String token ) {
        this.token = "Bearer " + token;
    }

    @Override
    public void filter( ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add(FILTER_HEADER_KEY, token);
    }
}
