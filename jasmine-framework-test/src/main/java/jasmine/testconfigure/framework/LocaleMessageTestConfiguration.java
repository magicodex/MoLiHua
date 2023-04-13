package jasmine.testconfigure.framework;

import jasmine.framework.i18n.LocaleMessageProvider;
import jasmine.framework.common.util.I18nUtil;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class LocaleMessageTestConfiguration {

    @Bean
    public LocaleMessageProvider localeMessageProvider() {
        LocaleMessageProvider provider = Mockito.mock(LocaleMessageProvider.class);
        // 初始工具类
        I18nUtil.initUtil(provider);

        return provider;
    }

}
