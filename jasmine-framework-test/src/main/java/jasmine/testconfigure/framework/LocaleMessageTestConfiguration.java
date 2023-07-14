package jasmine.testconfigure.framework;

import jasmine.framework.common.util.I18nUtil;
import jasmine.framework.i18n.LocaleMessageProvider;
import jasmine.framework.i18n.builder.MessageSourceBuilder;
import jasmine.framework.i18n.impl.DefaultLocaleMessageProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class LocaleMessageTestConfiguration {
    private final LocaleMessageTestProperties localeMessageProperties;

    public LocaleMessageTestConfiguration(LocaleMessageTestProperties localeMessageProperties) {
        this.localeMessageProperties = localeMessageProperties;
    }

    @Bean
    public LocaleMessageProvider localeMessageProvider() {
        LocaleMessageProvider provider = new DefaultLocaleMessageProvider(messageSource());
        // 初始工具类
        I18nUtil.initUtil(provider);

        return provider;
    }

    @Bean
    public MessageSource messageSource() {
        MessageSourceBuilder builder = new MessageSourceBuilder();
        // 多语言资源路径
        builder.setResourceBaseNames(localeMessageProperties.getResourceBaseNames());
        // 多语言资源编码
        builder.setEncoding(localeMessageProperties.getEncoding());
        // 多语言常量路径
        builder.setConstantPatterns(localeMessageProperties.getConstantPatterns());

        return builder.build();
    }

}
