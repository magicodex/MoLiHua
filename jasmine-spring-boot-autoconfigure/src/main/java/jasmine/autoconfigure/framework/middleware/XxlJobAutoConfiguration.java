package jasmine.autoconfigure.framework.middleware;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import jasmine.framework.job.JobExecutor;
import jasmine.framework.job.xxljob.XxlJobHandlerDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author mh.z
 */
@EnableConfigurationProperties(XxlJobProperties.class)
@ConditionalOnProperty(name = {"jasmine.xxl.job.enabled"}, havingValue = "true")
@Configuration
public class XxlJobAutoConfiguration implements SmartInitializingSingleton, ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(XxlJobAutoConfiguration.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        XxlJobAutoConfiguration.applicationContext = applicationContext;
    }

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobProperties xxlJobProperties) {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobProperties.Executor executor = xxlJobProperties.getExecutor();

        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdmin().getAddresses());
        xxlJobSpringExecutor.setAppname(executor.getAppName());
        xxlJobSpringExecutor.setAddress(executor.getAddress());
        xxlJobSpringExecutor.setIp(executor.getIp());
        xxlJobSpringExecutor.setPort(executor.getPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(executor.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(executor.getLogRetentionDays());

        return xxlJobSpringExecutor;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, JobExecutor> executorMap = applicationContext.getBeansOfType(JobExecutor.class);

        executorMap.forEach((name, executor) -> {
            XxlJobHandlerDelegate handler = new XxlJobHandlerDelegate(executor);
            XxlJobExecutor.registJobHandler(executor.getName(), handler);
        });
    }

}
