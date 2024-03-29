package jasmine.testconfigure.framework;

import jasmine.framework.concurrent.AsyncTaskProvider;
import jasmine.framework.concurrent.AsyncTaskUtil;
import jasmine.mock.framework.concurrent.MockAsyncTaskProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class AsyncTaskTestConfiguration {

    @Bean
    public AsyncTaskProvider asyncTaskProvider() {
        AsyncTaskProvider provider = new MockAsyncTaskProvider();
        // 初始工具类
        AsyncTaskUtil.initUtil(provider);

        return provider;
    }

}
