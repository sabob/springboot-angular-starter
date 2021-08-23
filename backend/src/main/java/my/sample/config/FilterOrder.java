package my.sample.config;

import org.springframework.core.Ordered;
/**
 * Below we set out the order of filters.
 * 1) Authentication must be first
 * 2) Context filter next in order to setup a singleton approach to retrieve ServletContext, HttpRequest and HttpResponse
 * 3) The Charon proxy is next to forward requests to remote servers
 * 4) Other filter, servlets etc can follow after the top three
 */
public class FilterOrder {

    public static final int ERROR_FILTER = Ordered.HIGHEST_PRECEDENCE;

    public static final int CORS_FILTER = Ordered.HIGHEST_PRECEDENCE;

    // This variable is the order Spring uses for it's authentication mechanism. We don't use it it's simply for informational purposes
    public static final int AUTHENTICATION_FILTER = -100;

    public static final int RESOURCE_CACHE_FILTER = 5;

    public static final int REQUEST_CONTEXT_FILER = 10;

    public static final int CHARON_PROXY = 20;
}
