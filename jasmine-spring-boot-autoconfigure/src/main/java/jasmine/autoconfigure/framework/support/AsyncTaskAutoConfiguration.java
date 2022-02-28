package jasmine.autoconfigure.framework.support;

import jasmine.core.context.handler.ContextHandlerFacade;
import jasmine.framework.concurrent.AsyncExecutorBuilder;
import jasmine.framework.concurrent.AsyncExecutorTaskProvider;
import jasmine.framework.concurrent.AsyncTaskDecorator;
import jasmine.framework.concurrent.AsyncTaskProvider;
import jasmine.framework.concurrent.AsyncTaskUtil;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;

/**
 * @author mh.z
 */
@AutoConfigureBefore(TaskExecutionAutoConfiguration.class)
@Configuration
public class AsyncTaskAutoConfiguration implements AsyncConfigurer {
    private ContextHandlerFacade handlerFacade;

    public AsyncTaskAutoConfiguration(ContextHandlerFacade handlerFacade) {
        this.handlerFacade = handlerFacade;
    }

    @Bean(AsyncAnnotationBeanPostProcessor.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    @Override
    public Executor getAsyncExecutor() {
        AsyncExecutorBuilder builder = new AsyncExecutorBuilder();
        builder.setTaskDecorator(taskDecorator(handlerFacade));

        return builder.build();
    }

    @Bean
    public AsyncTaskProvider asyncTaskProvider(Executor executor) {
        AsyncTaskProvider provider = new AsyncExecutorTaskProvider(executor);
        // 初始工具类
        AsyncTaskUtil.initUtil(provider);

        return provider;
    }

    @Bean
    public TaskDecorator taskDecorator(ContextHandlerFacade handlerFacade) {
        return new AsyncTaskDecorator(handlerFacade);
    }

}