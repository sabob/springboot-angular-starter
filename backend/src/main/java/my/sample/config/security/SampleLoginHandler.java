package my.sample.config.security;

import my.sample.util.AppUtils;
import my.sample.util.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class SampleLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationFailureHandler {

    private String contextPath;

    public SampleLoginHandler( String contextPath ) {
        this.contextPath = contextPath;
    }

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws ServletException, IOException {

        SampleToken token = AppUtils.createSampleToken( request );
        String value = AppUtils.toJson( token );
        response.getWriter().write( value );

        String base64Token = Base64.getEncoder().encodeToString( value.getBytes() );
        Cookie cookie = new Cookie( Constants.APP_COOKIE_NAME, base64Token );

        long dayAsLong = TimeUnit.DAYS.toMillis(1);
        int dayAsInt = Math.toIntExact(dayAsLong);
        cookie.setMaxAge( dayAsInt ); // set cookie for 24 hours
        cookie.setHttpOnly( false );
        cookie.setPath( contextPath );
        response.addCookie( cookie ); //add cookie to response

        super.onAuthenticationSuccess( request, response, authentication );
    }

    @Override
    public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response, AuthenticationException exception ) throws IOException, ServletException {
        System.out.println("AUTH FAILED");
    }
}
