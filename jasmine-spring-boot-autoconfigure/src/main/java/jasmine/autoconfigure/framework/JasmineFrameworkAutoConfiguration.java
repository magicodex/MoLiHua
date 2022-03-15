package jasmine.autoconfigure.framework;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QSpringUtil;
import jasmine.framework.context.ContextHandlerFacade;
import jasmine.framework.context.ContextHandlerFacadeBean;
import jasmine.framework.context.ContextInitAndClearHelper;
import jasmine.framework.context.CustomInitializingSingletonScanBean;
import jasmine.framework.context.InitSupportScanBean;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.framework.context.handler.FrameworkContextHandler;
import jasmine.framework.context.handler.web.RequestContextHandler;
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
    public ContextHandlerFacade contextHandlerFacade() {
        ContextHandlerFacade handlerFacade = new ContextHandlerFacadeBean();

        // 初始工具类
        ContextInitAndClearHelper.initUtil(handlerFacade);

        return handlerFacade;
    }

    @Bean
    public CustomInitializingSingletonScanBean customInitializingSingletonScanBean() {
        return new CustomInitializingSingletonScanBean();
    }

}
