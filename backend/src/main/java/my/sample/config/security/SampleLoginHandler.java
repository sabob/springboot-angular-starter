package my.sample.config.security;

import my.sample.util.AppUtils;
import my.sample.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class SampleLoginHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationFailureHandler {

    private String contextPath;

    public SampleLoginHandler( String contextPath ) {
        this.contextPath = contextPath;
    }

    @Override
    protected void handle( HttpServletRequest request, HttpServletResponse response,
                           Authentication authentication ) throws IOException, ServletException {

        SampleToken token = AppUtils.createSampleToken( request );
        String value = AppUtils.toJson( token );
        response.getWriter().write( value );

        String base64Token = Base64.getEncoder().encodeToString( value.getBytes() );
        Cookie cookie = new Cookie( Constants.APP_COOKIE_NAME, base64Token );
        int hoursInOneDay = 24 * 60 * 60 * 1000;
        cookie.setMaxAge( hoursInOneDay ); // set cookie for 24 hours
        cookie.setHttpOnly( false );
        cookie.setPath( contextPath );
        response.addCookie( cookie ); //add cookie to response

        response.setStatus( HttpStatus.OK.value() );
        response.setContentType( MediaType.APPLICATION_JSON.toString() );
    }

    @Override
    public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response, AuthenticationException exception ) throws IOException, ServletException {
        System.out.println("AUTH FAILED");
    }
}
