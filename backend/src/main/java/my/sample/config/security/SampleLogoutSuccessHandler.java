package my.sample.config.security;

import my.sample.util.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleLogoutSuccessHandler implements LogoutSuccessHandler {

    private String contextPath;

    public SampleLogoutSuccessHandler( String contextPath ) {
        this.contextPath = contextPath;
    }

    @Override
    public void onLogoutSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) {

        Cookie cookie = new Cookie( Constants.APP_COOKIE_NAME, "" );
        cookie.setMaxAge( -1 );
        cookie.setPath( contextPath );
        response.addCookie( cookie );
        response.setStatus( HttpServletResponse.SC_OK ); // Spring logout by default redirect to '/', so we override and return ok
    }
}
