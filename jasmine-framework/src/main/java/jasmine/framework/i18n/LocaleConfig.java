package jasmine.framework.i18n;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Properties;

/**
 * <p>
 * 配置多语言。
 * </p>
 *
 * @author mh.z
 */
@Configuration
public class LocaleConfig {

    /**  多语言资源路径 */
    private static final String MESSAGE_PATH_PREFIX = "message/messages";
    /** 多语言资源编码 */
    private static final String MESSAGE_DEFAULT_ENCODING = "UTF-8";
    /** 多语言常量路径 */
    private static final String MESSAGE_CONSTANT_PATH = "classpath*:/**/constant/*Messages.class";

    /** 多语言的cookie名 */
    private static final String COOKIE_LANGUAGE = "LANG";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_PATH_PREFIX);
        messageSource.setDefaultEncoding(MESSAGE_DEFAULT_ENCODING);
        // 扫描多语言常量
        Properties commonMessages = DeclareI18nScanUtil.scan(MESSAGE_CONSTANT_PATH);
        messageSource.setCommonMessages(commonMessages);

        return messageSource;
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

}
