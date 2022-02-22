package jasmine.autoconfigure.security.support;

import jasmine.security.support.SecurityContextCopyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class SecurityContextAutoConfiguration {

    @Bean
    public SecurityContextCopyHandler securityContextCopyHandler() {
        return new SecurityContextCopyHandler();
    }

}
