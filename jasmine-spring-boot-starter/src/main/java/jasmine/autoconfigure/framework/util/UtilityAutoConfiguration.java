package jasmine.autoconfigure.framework.util;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.i18n.LocaleMessageProvider;
import jasmine.core.util.QI18nUtil;
import jasmine.core.util.QSpringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class UtilityAutoConfiguration implements InitializingBean {
    private RuntimeProvider runtimeProvider;
    private LocaleMessageProvider localeMessageProvider;

    public UtilityAutoConfiguration(RuntimeProvider runtimeProvider,
                                    LocaleMessageProvider localeMessageProvider) {
        this.runtimeProvider = runtimeProvider;
        this.localeMessageProvider = localeMessageProvider;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        QSpringUtil.initUtil(runtimeProvider);

        QI18nUtil.initUtil(localeMessageProvider);
    }

}
