package jasmine.testconfigure.framework;

import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.context.SubjectProvider;
import jasmine.framework.common.util.SpringUtil;
import jasmine.framework.context.init.CustomInitializingSingletonScanBean;
import jasmine.framework.context.init.InitSupportScanBean;
import jasmine.framework.context.impl.SpringRuntimeProvider;
import jasmine.mock.core.context.MockSubjectProvider;
import jasmine.test.constant.TestConstants;
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
        SpringUtil.initUtil(runtimeProvider);

        return runtimeProvider;
    }

    @Bean
    public SubjectProvider subjectProvider() {
        SubjectProvider subjectProvider = new MockSubjectProvider(TestConstants.TEST_USER_ID_UNKNOWN,
                TestConstants.TEST_TENANT_ID_1);
        // 初始工具类
        CurrentSubject.initUtil(subjectProvider);

        return subjectProvider;
    }

    @Bean
    public InitSupportScanBean initSupportScanBean(RuntimeProvider runtimeProvider) {
        return new InitSupportScanBean(runtimeProvider);
    }

    @Bean
    public CustomInitializingSingletonScanBean customInitializingSingletonScanBean() {
        return new CustomInitializingSingletonScanBean();
    }

}
