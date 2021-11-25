package jasmine.framework.config;

import jasmine.framework.i18n.DeclareI18nScanUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Properties;

@Configuration
public class LocaleConfig {

    private static final String MESSAGE_PATH_PREFIX = "message/messages";
    private static final String MESSAGE_DEFAULT_ENCODING = "UTF-8";
    private static final String MESSAGE_CONSTANT_PATH = "classpath*:/**/constant/*Messages.class";

    private static final String COOKIE_LANGUAGE = "LANG";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_PATH_PREFIX);
        messageSource.setDefaultEncoding(MESSAGE_DEFAULT_ENCODING);
        Properties commonMessages = DeclareI18nScanUtil.scan(MESSAGE_CONSTANT_PATH);
        messageSource.setCommonMessages(commonMessages);

        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName(COOKIE_LANGUAGE);

        return resolver;
    }

}
