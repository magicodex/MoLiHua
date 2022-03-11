package jasmine.mock.testconfigure.support;

import jasmine.framework.concurrent.AsyncExecutorTaskProvider;
import jasmine.framework.concurrent.AsyncTaskProvider;
import jasmine.framework.concurrent.AsyncTaskUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author mh.z
 */
@Configuration
public class AsyncTaskTestConfiguration {

    @Bean
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(1);
    }

    @Bean
    public AsyncTaskProvider asyncTaskProvider(Executor executor) {
        AsyncTaskProvider provider = new AsyncExecutorTaskProvider(executor);
        // 初始工具类
        AsyncTaskUtil.initUtil(provider);

        return provider;
    }

}
