package my.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SampleApplication extends SpringBootServletInitializer {

    public static void main( String[] args ) {

// In order to have Fiddler monitor network traffic from a Java app, use config below.
// Answer from Stackoverflow -> https://stackoverflow.com/questions/8549749/how-to-capture-https-with-fiddler-in-java
//
// If the traffic is through https, then you need to import the Fiddler Keystore into Java and reference it in
// your app java as specified below
//
//        System.setProperty("http.proxyHost", "127.0.0.1");
//        System.setProperty("https.proxyHost", "127.0.0.1");
//        System.setProperty("http.proxyPort", "8888");
//        System.setProperty("https.proxyPort", "8888");
//        System.setProperty("javax.net.ssl.trustStore", "c:\\jdk8\\bin\\FiddlerKeystore");
//        System.setProperty("javax.net.ssl.trustStorePassword", "password");
        //System.setProperty("javax.net.debug", "all");

        SpringApplication.run( SampleApplication.class, args );
    }

}
