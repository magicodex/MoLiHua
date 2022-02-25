package jasmine.mock.testconfigure;

import jasmine.core.i18n.LocaleMessageProvider;
import jasmine.core.util.QI18nUtil;
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
        QI18nUtil.initUtil(provider);

        return provider;
    }

}
