package jasmine.autoconfigure.framework.support;

import jasmine.framework.concurrent.impl.AsyncExecutorTaskProvider;
import jasmine.framework.concurrent.impl.AsyncTaskDecorator;
import jasmine.framework.concurrent.AsyncTaskProvider;
import jasmine.framework.concurrent.AsyncTaskUtil;
import jasmine.framework.context.thread.ContextHandlerFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;

/**
 * @author mh.z
 */
@Configuration
public class AsyncTaskAutoConfiguration implements AsyncConfigurer {
    private ContextHandlerFacade contextHandlerFacade;

    private static final String EXECUTOR_BEAN_NAME = AsyncAnnotationBeanPostProcessor
            .DEFAULT_TASK_EXECUTOR_BEAN_NAME;

    public AsyncTaskAutoConfiguration(ContextHandlerFacade contextHandlerFacade) {
        this.contextHandlerFacade = contextHandlerFacade;
    }

    @Bean
    public TaskDecorator taskDecorator() {
        return new AsyncTaskDecorator(contextHandlerFacade);
    }

    @Bean
    public AsyncTaskProvider asyncTaskProvider(@Qualifier(EXECUTOR_BEAN_NAME) Executor executor) {
        AsyncTaskProvider provider = new AsyncExecutorTaskProvider(executor);
        // 初始工具类
        AsyncTaskUtil.initUtil(provider);

        return provider;
    }

}
