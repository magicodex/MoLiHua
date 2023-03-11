package jasmine.autoconfigure.framework;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.SpringUtil;
import jasmine.framework.cache.context.RequestScopeCacheContextHandler;
import jasmine.framework.context.thread.ContextManagementUtil;
import jasmine.framework.context.CustomInitializingSingletonScanBean;
import jasmine.framework.context.InitSupportScanBean;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.framework.context.thread.ContextHandlerFacade;
import jasmine.framework.context.thread.ContextHandlerFacadeBean;
import jasmine.framework.context.thread.framework.FrameworkContextHandler;
import jasmine.framework.context.thread.web.RequestContextHandler;
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
