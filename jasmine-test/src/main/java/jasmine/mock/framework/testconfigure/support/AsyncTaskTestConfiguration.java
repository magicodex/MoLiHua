package jasmine.mock.framework.testconfigure.support;

import jasmine.framework.concurrent.AsyncTaskProvider;
import jasmine.framework.concurrent.AsyncTaskUtil;
import jasmine.mock.framework.concurrent.MockAsyncTaskProvider;
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
        AsyncTaskProvider provider = new MockAsyncTaskProvider();
        // 初始工具类
        AsyncTaskUtil.initUtil(provider);

        return provider;
    }

}
