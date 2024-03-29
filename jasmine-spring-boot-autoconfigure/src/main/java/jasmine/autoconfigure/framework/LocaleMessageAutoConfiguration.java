package jasmine.autoconfigure.framework;

import jasmine.framework.common.util.I18nUtil;
import jasmine.framework.i18n.LocaleMessageProvider;
import jasmine.framework.i18n.builder.MessageSourceBuilder;
import jasmine.framework.i18n.impl.DefaultLocaleMessageProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * @author mh.z
 */
@EnableConfigurationProperties(LocaleMessageProperties.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@Configuration
public class LocaleMessageAutoConfiguration {
    /** 多语言的cookie名 */
    private static final String COOKIE_LANGUAGE = "LANG";

    @ConditionalOnMissingBean(MessageSource.class)
    @Bean
    public MessageSource messageSource(LocaleMessageProperties properties) {
        MessageSourceBuilder builder = new MessageSourceBuilder();
        // 多语言资源路径
        builder.setResourceBaseNames(properties.getResourceBaseNames());
        // 多语言资源编码
        builder.setEncoding(properties.getEncoding());
        // 多语言常量路径
        builder.setConstantPatterns(properties.getConstantPatterns());

        return builder.build();
    }

    /**
     * 切换语言的策略
     *
     * @return
     */
    @ConditionalOnMissingBean(LocaleResolver.class)
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName(COOKIE_LANGUAGE);

        return resolver;
    }

    @ConditionalOnMissingBean(LocaleMessageProvider.class)
    @Bean
    public LocaleMessageProvider localeMessageProvider(MessageSource messageSource) {
        LocaleMessageProvider localeMessageProvider = new DefaultLocaleMessageProvider(messageSource);

        // 初始多语言工具类
        I18nUtil.initUtil(localeMessageProvider);

        return localeMessageProvider;
    }

}
