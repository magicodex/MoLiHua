package jasmine.testconfigure.framework;

import jasmine.framework.common.util.I18nUtil;
import jasmine.framework.i18n.LocaleMessageProvider;
import jasmine.framework.i18n.builder.MessageSourceBuilder;
import jasmine.framework.i18n.impl.DefaultLocaleMessageProvider;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author mh.z
 */
@Configuration
public class LocaleMessageTestConfiguration {

    /**  多语言资源路径 */
    @Value("${jasmine.i18n.message.resourceBaseNames:message/messages}")
    private String[] resourceBaseNames = new String[]{"message/messages"};

    /** 多语言资源编码 */
    @Value("${jasmine.i18n.message.encoding:UTF-8}")
    private String encoding = "UTF-8";

    /** 多语言常量路径 */
    @Value("${jasmine.i18n.message.constantPatterns:classpath*:/jasmine/**/constant/*MessageConstants.class}")
    private String[] constantPatterns;

    @Bean
    public LocaleMessageProvider localeMessageProvider() {
        LocaleMessageProvider provider = new DefaultLocaleMessageProvider(messageSource());
        // 初始工具类
        I18nUtil.initUtil(provider);

        return provider;
    }

    @Primary
    @Bean
    public MessageSource messageSource() {
        MessageSourceBuilder builder = new MessageSourceBuilder();
        // 多语言资源路径
        builder.setResourceBaseNames(resourceBaseNames);
        // 多语言资源编码
        builder.setEncoding(encoding);
        // 多语言常量路径
        builder.setConstantPatterns(constantPatterns);

        return builder.build();
    }

}
