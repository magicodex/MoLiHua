package jasmine.autoconfigure.framework.context;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.SubjectProvider;
import jasmine.framework.context.InitSupportScanBean;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.security.subject.UserSubjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class ContextAutoConfiguration {
    private SubjectProvider subjectProvider;

    @Bean
    public SpringRuntimeProvider runtimeProvider() {
        return new SpringRuntimeProvider();
    }

    @Bean
    public SubjectProvider subjectProvider() {
        subjectProvider = new UserSubjectProvider();

        CurrentSubject.initCurrentSubject(subjectProvider);
        return subjectProvider;
    }

    @Bean
    public InitSupportScanBean initSupportScanBean(SpringRuntimeProvider runtimeProvider) {
        return new InitSupportScanBean(runtimeProvider);
    }

}
