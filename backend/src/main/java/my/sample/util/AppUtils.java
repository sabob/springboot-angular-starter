package my.sample.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.sample.config.StaticContext;
import my.sample.config.security.SampleToken;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class AppUtils {

    private static final int BUFF_SIZE = 4096 * 8;

    private static BuildProperties buildProperties;

    private static ObjectMapper mapper = new ObjectMapper();

    public static SampleToken createSampleToken( HttpServletRequest request) {
        String activeProfile = getActiveProfile();

        ApplicationContext ac = StaticContext.getApplicationContext();
        BuildProperties buildProperties = getBuildProperties( ac );

        SampleToken token = new SampleToken();
        token.setAdmin( AppUtils.isAdmin() );
        token.setProfile( activeProfile );
        token.setVersion( buildProperties.getVersion() );

        String username = getUsername();
        token.setUsername( username );
        return token;
    }

    public static BuildProperties getBuildProperties( ApplicationContext ac ) {
        if ( buildProperties != null ) {
            return buildProperties;
        }

        try {
            buildProperties = ac.getBean( BuildProperties.class );
            return buildProperties;

        } catch ( Exception ex ) {
            buildProperties = buildProperties();

            if ( buildProperties == null ) {
                Properties properties = new Properties();
                properties.setProperty( "version", "x.x.x" );
                buildProperties = new BuildProperties( properties );

            }
        }
        return buildProperties;
    }

    public static String getActiveProfile() {
        String[] profiles = StaticContext.getApplicationContext().getEnvironment().getActiveProfiles();
        if ( profiles.length == 0 ) {
            return "unknown";
        }
        return profiles[0];
    }

    public static String resourceToString( String path ) {

        ClassPathResource resource = new ClassPathResource( path );
        try ( InputStream is = resource.getInputStream() ) {
            byte[] bs = toByteArray( is );
            String content = new String( bs );

            return content;

        } catch ( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }

    public static byte[] toByteArray( InputStream input ) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFF_SIZE];
            int count;
            while ( (count = input.read( buffer )) != -1 ) {
                output.write( buffer, 0, count );
            }
            byte[] contents = output.toByteArray();
            return contents;
        } catch ( IOException ex ) {
            throw new RuntimeException( ex );
        }
    }

    public static List<String> getRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = getRoles( auth );
        return roles;
    }

    public static List<String> getRoles( Authentication auth ) {
        List<String> list = new ArrayList<>();
        if ( auth == null ) {
            return list;
        }

        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
        roles.stream().forEach( role -> {
            String value = (( GrantedAuthority ) role).getAuthority();
            list.add( value );
        } );

        return list;
    }

    public static boolean isAdmin( Authentication auth ) {
        List<String> roles = getRoles( auth );
        boolean admin = roles.contains( Roles.ADMIN );
        return admin;
    }

    public static String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public static boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return isAdmin( auth );
    }

    public static BuildProperties buildProperties() {
        try {
            String content = resourceToString( "META-INF/build-info.properties" );

            Properties properties = new Properties();
            properties.load( new StringReader( content ) );
            return new BuildProperties( properties );
        } catch ( Exception ex ) {
            return null;
        }
    }

    public static String toJson( Object obj ) {
        String json = null;

        try {
            json = mapper.writeValueAsString( obj );

        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
            json = obj.toString();
        }
        return json;
    }
}
