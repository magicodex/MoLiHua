package jasmine.autoconfigure.framework.middleware;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import jasmine.framework.job.JobExecutor;
import jasmine.framework.job.xxljob.XxlJobHandlerDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author mh.z
 */
@ConditionalOnProperty(name = {"jasmine.xxl.job.enabled"}, havingValue = "true")
@Configuration
public class XxlJobAutoConfiguration implements SmartInitializingSingleton, ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(XxlJobAutoConfiguration.class);
    private static ApplicationContext applicationContext;

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.address}")
    private String address;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        XxlJobAutoConfiguration.applicationContext = applicationContext;
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