package jasmine.mock.testconfigure;

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
        AsyncTaskProvider provider = new AsyncTaskProvider(executor);
        AsyncTaskUtil.initUtil(provider);

        return provider;
    }

}
