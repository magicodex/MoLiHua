package jasmine.mock.testconfigure;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.context.SubjectProvider;
import jasmine.core.util.QSpringUtil;
import jasmine.framework.context.InitSupportScanBean;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.mock.context.TestSubjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class JasmineFrameworkTestConfiguration {

    @Bean
    public SpringRuntimeProvider runtimeProvider() {
        SpringRuntimeProvider runtimeProvider = new SpringRuntimeProvider();
        // 初始工具类
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
    public InitSupportScanBean initSupportScanBean(RuntimeProvider runtimeProvider) {
        return new InitSupportScanBean(runtimeProvider);
    }

}
