package jasmine.autoconfigure.framework.support;

import jasmine.framework.concurrent.AsyncExecutorBuilder;
import jasmine.framework.concurrent.AsyncTaskDecoratorBean;
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
    /** 核心线程数 */
    private static final int CORE_POOL_SIZE = 10;
    /** 最大线程数 */
    private static final int MAX_POOL_SIZE = 20;
    /** 队列容量 */
    private static final int QUEUE_CAPACITY = 10;
    /** 允许空闲的最大时间 */
    private static final int KEEP_ALIVE_SECONDS = 1800;

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        AsyncExecutorBuilder builder = new AsyncExecutorBuilder();
        builder.setTaskDecorator(taskDecorator());

        return builder.build();
    }

    @Bean(AsyncAnnotationBeanPostProcessor.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    public AsyncTaskProvider asyncTaskProvider(Executor executor) {
        AsyncTaskProvider provider = new AsyncTaskProvider(executor);
        // 初始异步工具类
        AsyncTaskUtil.initUtil(provider);

        return provider;
    }

    @Bean
    public TaskDecorator taskDecorator() {
        return new AsyncTaskDecoratorBean();
    }

}
