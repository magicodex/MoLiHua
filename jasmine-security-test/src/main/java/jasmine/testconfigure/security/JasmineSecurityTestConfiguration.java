package jasmine.testconfigure.security;

import jasmine.framework.context.SubjectProvider;
import jasmine.mock.framework.context.MockSubjectProvider;
import jasmine.security.integration.SecurityContextHandler;
import jasmine.security.integration.SecurityTenantConfigProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mh.z
 */
@Configuration
public class JasmineSecurityTestConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SubjectProvider subjectProvider() {
        return new MockSubjectProvider();
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
