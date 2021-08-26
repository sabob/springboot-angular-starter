package my.sample.config.security;

import my.sample.util.Constants;
import my.sample.util.Roles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;
import org.springframework.boot.actuate.web.mappings.MappingsEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value( "${spring.profiles.active}" )
    private String activeProfile;

    @Value( "${server.servlet.context-path}" )
    private String contextPath;

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser( "user" )
                .password( encoder.encode( "password" ) )
                .roles( Roles.USER, Roles.ACTUATOR )
                .and()
                .withUser( "admin" )
                .password( encoder.encode( "password" ) )
                .roles( Roles.ADMIN, Roles.USER, Roles.ACTUATOR );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.authorizeRequests( ( requests ) -> {
            requests.requestMatchers( EndpointRequest.to( HealthEndpoint.class, InfoEndpoint.class, PrometheusScrapeEndpoint.class ) ).permitAll();
            requests.requestMatchers( EndpointRequest.toAnyEndpoint().excluding( MappingsEndpoint.class ) ).hasRole( Roles.ACTUATOR );
            requests.requestMatchers( PathRequest.toStaticResources().atCommonLocations() ).permitAll();
            requests.antMatchers(
                            "index.html",
                            "/*.js",
                            "/*.css",
                            "/*.png",
                            "/*.woff",
                            "/*.woff2",
                            "/*.ttf",
                            "/*.ico",
                            "/api/openapi.json" )
                    .permitAll();

            requests.anyRequest().fullyAuthenticated();
        } );

        SampleLogoutSuccessHandler logoutHandler = new SampleLogoutSuccessHandler( contextPath );
        SampleLoginHandler loginHandler = new SampleLoginHandler( contextPath );
//    http.headers()
//      .contentSecurityPolicy("default-src 'none'; script-src 'unsafe-inline'; style-src 'self'; form-action 'self'; img-src 'self'");
        http.formLogin()
            //.loginPage( "/login" ).permitAll()
            .loginProcessingUrl( "/login" ).permitAll()
            .successHandler( loginHandler )
            .failureHandler( loginHandler )

            .failureUrl( "/login?error" ).permitAll()
            .and()
            .logout()
            .logoutUrl( "/logout" )
            .logoutSuccessHandler( logoutHandler )
            .invalidateHttpSession( true )
            .clearAuthentication( true )
//            .logoutSuccessHandler(new LogoutSuccessHandler() {
//                @Override
//                public void onLogoutSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                    System.out.println("LOGOUT NOW?");
//                    redirectStrategy.sendRedirect(request, response, "/"); // Redirect to home
//                }
//            })

            // Cannot use deleteCookies because it appends a trailing slash to the path of the cookie. This should be fixed in Spring v5.6.0
            //.deleteCookies( Constants.APP_COOKIE_NAME );
            .deleteCookies( Constants.JSESSIONID_COOKIE_NAME );

        http.logout( LogoutConfigurer::permitAll );
        //http.httpBasic(); // API uses basic authentication
        http.csrf().disable();
        http.cors();
    }
}
