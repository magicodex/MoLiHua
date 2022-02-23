package jasmine.autoconfigure.framework;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.SubjectProvider;
import jasmine.core.util.QSpringUtil;
import jasmine.framework.context.InitSupportScanBean;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.security.subject.UserSubjectDetailsService;
import jasmine.security.subject.UserSubjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasmineFrameworkAutoConfiguration {

    @Bean
    public SpringRuntimeProvider runtimeProvider() {
        SpringRuntimeProvider runtimeProvider = new SpringRuntimeProvider();

        QSpringUtil.initUtil(runtimeProvider);

        return runtimeProvider;
    }

    @Bean
    public SubjectProvider subjectProvider(UserSubjectDetailsService userSubjectDetailsService) {
        SubjectProvider subjectProvider = new UserSubjectProvider(userSubjectDetailsService);

        CurrentSubject.initCurrentSubject(subjectProvider);
        return subjectProvider;
    }

    @Bean
    public InitSupportScanBean initSupportScanBean(SpringRuntimeProvider runtimeProvider) {
        return new InitSupportScanBean(runtimeProvider);
    }

}
