package jasmine.mock.testautoconfigure;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.context.SubjectProvider;
import jasmine.core.i18n.LocaleMessageProvider;
import jasmine.core.util.QI18nUtil;
import jasmine.core.util.QSpringUtil;
import jasmine.framework.concurrent.AsyncTaskProvider;
import jasmine.framework.concurrent.AsyncTaskUtil;
import jasmine.framework.context.InitSupportScanBean;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.mock.context.TestSubjectProvider;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author mh.z
 */
@Configuration
public class FrameworkTestConfig {

    @Bean
    public SpringRuntimeProvider runtimeProvider() {
        SpringRuntimeProvider runtimeProvider = new SpringRuntimeProvider();
        QSpringUtil.initUtil(runtimeProvider);

        return runtimeProvider;
    }

    @Bean
    public SubjectProvider subjectProvider() {
        SubjectProvider subjectProvider = new TestSubjectProvider();
        // 初始工具类
        CurrentSubject.initUtil(subjectProvider);

        return subjectProvider;
    }

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

    @Bean
    public LocaleMessageProvider localeMessageProvider() {
        LocaleMessageProvider provider = Mockito.mock(LocaleMessageProvider.class);
        QI18nUtil.initUtil(provider);

        return provider;
    }

    @Bean
    public InitSupportScanBean initSupportScanBean(RuntimeProvider runtimeProvider) {
        return new InitSupportScanBean(runtimeProvider);
    }

}
