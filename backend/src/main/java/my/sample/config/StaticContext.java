package my.sample.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticContext implements ApplicationContextAware {

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext( ApplicationContext ac ) throws BeansException {
        this.ac = ac;
   }

    public static ApplicationContext getApplicationContext() {

        if ( ac == null ) {
            throw new IllegalStateException(
                "Attempting to retrieve Spring ApplicationContext but ApplicationContext is null. Could mean Spring broke on startup." );

        }

        return ac;
    }
}
