package jasmine.autoconfigure.framework.support;

import jasmine.core.i18n.LocaleMessageProvider;
import jasmine.core.util.QI18nUtil;
import jasmine.framework.i18n.DefaultLocaleMessageProvider;
import jasmine.framework.i18n.MessageSourceBuilder;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * @author mh.z
 */
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@Configuration
public class LocaleMessageAutoConfiguration {
    /** 多语言的cookie名 */
    private static final String COOKIE_LANGUAGE = "LANG";

    @Bean
    public MessageSource messageSource() {
        MessageSourceBuilder builder = new MessageSourceBuilder();

        return builder.build();
    }

    /**
     * 切换语言的策略
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName(COOKIE_LANGUAGE);

        return resolver;
    }

    @Bean
    public LocaleMessageProvider localeMessageProvider(MessageSource messageSource) {
        LocaleMessageProvider localeMessageProvider = new DefaultLocaleMessageProvider(messageSource);

        // 初始多语言工具类
        QI18nUtil.initUtil(localeMessageProvider);

        return localeMessageProvider;
    }

}
