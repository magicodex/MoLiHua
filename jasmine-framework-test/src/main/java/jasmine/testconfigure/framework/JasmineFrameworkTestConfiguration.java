package jasmine.testconfigure.framework;

import jasmine.framework.cache.integration.RequestScopeCacheContextHandler;
import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.context.SubjectProvider;
import jasmine.framework.common.util.SpringUtil;
import jasmine.framework.context.impl.thread.ContextHandlerFacadeBean;
import jasmine.framework.context.init.CustomInitializingSingletonScanBean;
import jasmine.framework.context.init.InitSupportScanBean;
import jasmine.framework.context.impl.SpringRuntimeProvider;
import jasmine.framework.context.thread.ContextHandlerFacade;
import jasmine.framework.context.thread.ContextManagementUtil;
import jasmine.framework.database.integration.DatabaseContextHandler;
import jasmine.framework.web.integration.RequestContextHandler;
import jasmine.mock.framework.context.MockSubjectProvider;
import jasmine.framework.test.constant.TestConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class JasmineFrameworkTestConfiguration {

    @Bean
    public SpringRuntimeProvider runtimeProvider() {
        SpringRuntimeProvider runtimeProvider = new SpringRuntimeProvider();
        // 初始工具类
        SpringUtil.initUtil(runtimeProvider);

        return runtimeProvider;
    }

    @Bean
    public SubjectProvider subjectProvider() {
        SubjectProvider subjectProvider = new MockSubjectProvider(TestConstants.TEST_USER_ID_UNKNOWN,
                TestConstants.TEST_TENANT_ID_1);
        // 初始工具类
        CurrentSubject.initUtil(subjectProvider);

        return subjectProvider;
    }

    @Bean
    public ContextHandlerFacade contextHandlerFacade() {
        ContextHandlerFacade handlerFacade = new ContextHandlerFacadeBean();
        // 初始工具类
        ContextManagementUtil.initUtil(handlerFacade);

        return handlerFacade;
    }

    @Bean
    public InitSupportScanBean initSupportScanBean(RuntimeProvider runtimeProvider) {
        return new InitSupportScanBean(runtimeProvider);
    }

    @Bean
    public CustomInitializingSingletonScanBean customInitializingSingletonScanBean() {
        return new CustomInitializingSingletonScanBean();
    }

    @Bean
    public RequestContextHandler requestContextHandler() {
        return new RequestContextHandler();
    }

    @Bean
    public DatabaseContextHandler databaseContextHandler() {
        return new DatabaseContextHandler();
    }

    @Bean
    public RequestScopeCacheContextHandler requestScopeCacheContextHandler() {
        return new RequestScopeCacheContextHandler();
    }

}
