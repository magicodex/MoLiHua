package jasmine.autoconfigure.framework;

import jasmine.framework.cache.integration.RequestScopeCacheContextHandler;
import jasmine.framework.common.util.SpringUtil;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.context.impl.SpringRuntimeProvider;
import jasmine.framework.context.impl.thread.ContextHandlerFacadeBean;
import jasmine.framework.context.init.CustomInitializingSingletonScanBean;
import jasmine.framework.context.init.InitSupportScanBean;
import jasmine.framework.context.thread.ContextHandlerFacade;
import jasmine.framework.context.thread.ContextManagementUtil;
import jasmine.framework.database.integration.DatabaseContextHandler;
import jasmine.framework.database.liquibase.BeanTaskChange;
import jasmine.framework.database.liquibase.CustomSpringLiquibase;
import jasmine.framework.web.integration.RequestContextHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class JasmineFrameworkAutoConfiguration {

    @ConditionalOnMissingBean(RuntimeProvider.class)
    @Bean
    public SpringRuntimeProvider runtimeProvider() {
        SpringRuntimeProvider runtimeProvider = new SpringRuntimeProvider();

        // 初始工具类
        SpringUtil.initUtil(runtimeProvider);

        return runtimeProvider;
    }

    @ConditionalOnMissingBean(InitSupportScanBean.class)
    @Bean
    public InitSupportScanBean initSupportScanBean(RuntimeProvider runtimeProvider) {
        return new InitSupportScanBean(runtimeProvider);
    }

    @ConditionalOnMissingBean(RequestContextHandler.class)
    @Bean
    public RequestContextHandler requestContextHandler() {
        return new RequestContextHandler();
    }

    @ConditionalOnMissingBean(DatabaseContextHandler.class)
    @Bean
    public DatabaseContextHandler databaseContextHandler() {
        return new DatabaseContextHandler();
    }

    @ConditionalOnMissingBean(RequestScopeCacheContextHandler.class)
    @Bean
    public RequestScopeCacheContextHandler requestScopeCacheContextHandler() {
        return new RequestScopeCacheContextHandler();
    }

    @ConditionalOnMissingBean(ContextHandlerFacade.class)
    @Bean
    public ContextHandlerFacade contextHandlerFacade() {
        ContextHandlerFacade handlerFacade = new ContextHandlerFacadeBean();

        // 初始工具类
        ContextManagementUtil.initUtil(handlerFacade);

        return handlerFacade;
    }

    @ConditionalOnMissingBean(CustomInitializingSingletonScanBean.class)
    @Bean
    public CustomInitializingSingletonScanBean customInitializingSingletonScanBean() {
        return new CustomInitializingSingletonScanBean();
    }

    @ConditionalOnMissingBean(CustomSpringLiquibase.class)
    @Bean
    public CustomSpringLiquibase customSpringLiquibase() {
        return new CustomSpringLiquibase();
    }

    @ConditionalOnMissingBean(BeanTaskChange.class)
    @Bean
    public BeanTaskChange beanTaskChange() {
        return new BeanTaskChange();
    }

}
