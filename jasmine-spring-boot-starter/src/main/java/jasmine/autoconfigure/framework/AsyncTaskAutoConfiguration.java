package jasmine.autoconfigure.framework;

import jasmine.framework.concurrent.AsyncTaskDecoratorBean;
import jasmine.framework.concurrent.AsyncTaskProvider;
import jasmine.framework.concurrent.AsyncTaskUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author mh.z
 */
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
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 核心线程数
        executor.setCorePoolSize(CORE_POOL_SIZE);
        // 最大线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 队列容量
        executor.setQueueCapacity(QUEUE_CAPACITY);
        // 允许空闲的最大时间
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        // 任务装饰器
        executor.setTaskDecorator(taskDecorator());

        return executor;
    }

    @Bean
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
