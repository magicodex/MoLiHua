package jasmine.autoconfigure.framework;

import jasmine.core.context.handler.ContextHandlerFacade;
import jasmine.core.context.CurrentSubject;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QSpringUtil;
import jasmine.framework.context.handler.ContextHandlerFacadeBean;
import jasmine.framework.context.InitSupportScanBean;
import jasmine.framework.context.handler.RequestContextHandler;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.security.subject.UserSubjectDetailsService;
import jasmine.security.subject.UserSubjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class JasmineFrameworkAutoConfiguration {

    @Bean
    public SpringRuntimeProvider runtimeProvider() {
        SpringRuntimeProvider runtimeProvider = new SpringRuntimeProvider();

        // 初始工具类
        QSpringUtil.initUtil(runtimeProvider);

        return runtimeProvider;
    }

    @Bean
    public UserSubjectProvider subjectProvider(UserSubjectDetailsService userSubjectDetailsService) {
        UserSubjectProvider subjectProvider = new UserSubjectProvider(userSubjectDetailsService);

        // 初始工具类
        CurrentSubject.initUtil(subjectProvider);

        return subjectProvider;
    }

    @Bean
    public InitSupportScanBean initSupportScanBean(RuntimeProvider runtimeProvider) {
        return new InitSupportScanBean(runtimeProvider);
    }

    @Bean
    public RequestContextHandler requestContextHandler() {
        return new RequestContextHandler();
    }

    @Bean
    public ContextHandlerFacade contextHandlerFacade() {
        return new ContextHandlerFacadeBean();
    }

}
