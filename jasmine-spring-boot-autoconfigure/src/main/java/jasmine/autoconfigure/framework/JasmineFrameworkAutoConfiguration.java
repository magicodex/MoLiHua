package jasmine.autoconfigure.framework;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.SpringUtil;
import jasmine.framework.cache.integration.RequestScopeCacheContextHandler;
import jasmine.framework.context.thread.ContextManagementUtil;
import jasmine.framework.context.init.CustomInitializingSingletonScanBean;
import jasmine.framework.context.init.InitSupportScanBean;
import jasmine.framework.context.impl.SpringRuntimeProvider;
import jasmine.framework.context.thread.ContextHandlerFacade;
import jasmine.framework.context.impl.thread.ContextHandlerFacadeBean;
import jasmine.framework.context.impl.thread.FrameworkContextHandler;
import jasmine.framework.web.integration.RequestContextHandler;
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
        SpringUtil.initUtil(runtimeProvider);

        return runtimeProvider;
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
    public FrameworkContextHandler frameworkContextHandler() {
        return new FrameworkContextHandler();
    }

    @Bean
    public RequestScopeCacheContextHandler requestScopeCacheContextHandler() {
        return new RequestScopeCacheContextHandler();
    }

    @Bean
    public ContextHandlerFacade contextHandlerFacade() {
        ContextHandlerFacade handlerFacade = new ContextHandlerFacadeBean();

        // 初始工具类
        ContextManagementUtil.initUtil(handlerFacade);

        return handlerFacade;
    }

    @Bean
    public CustomInitializingSingletonScanBean customInitializingSingletonScanBean() {
        return new CustomInitializingSingletonScanBean();
    }

}
