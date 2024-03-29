package jasmine.testconfigure.security;

import jasmine.mock.security.MockPasswordEncoder;
import jasmine.security.integration.SecurityContextHandler;
import jasmine.security.integration.SecurityTenantConfigProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mh.z
 */
@Configuration
public class JasmineSecurityTestConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MockPasswordEncoder();
    }

    @Bean
    public SecurityContextHandler securityContextHandler() {
        return new SecurityContextHandler();
    }

    @Bean
    public SecurityTenantConfigProcessor securityTenantConfigProcessor() {
        return new SecurityTenantConfigProcessor();
    }

}
