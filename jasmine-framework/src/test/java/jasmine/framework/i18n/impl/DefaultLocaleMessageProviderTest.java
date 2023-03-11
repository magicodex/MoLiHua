package jasmine.framework.i18n.impl;

import jasmine.framework.i18n.impl.DefaultLocaleMessageProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author mh.z
 */
public class DefaultLocaleMessageProviderTest {
    private Locale previousLocale;

    @Before
    public void setUp() {
        previousLocale = LocaleContextHolder.getLocale();
        LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
    }

    @After
    public void tearDown() {
        LocaleContextHolder.setLocale(previousLocale);
    }

    @Test
    public void testGetMessage() {
        MessageSource messageSource = Mockito.mock(MessageSource.class);
        // 这里只是把 messageKey 转换成大写
        Mockito.when(messageSource.getMessage(Mockito.any(), Mockito.any(), Mockito.any()))
                .then((answer) -> {
                    String messageKey = answer.getArgument(0);

                    return messageKey.toUpperCase();
                });

        LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        DefaultLocaleMessageProvider provider = new DefaultLocaleMessageProvider(messageSource);

        {
            String actual = provider.getMessage("Hello, world!");
            Assert.assertEquals("HELLO, WORLD!", actual);
        }

        {
            String actual = provider.getMessage("$Hello, world!");
            Assert.assertEquals("HELLO, WORLD!", actual);
        }
    }

    @Test
    public void testGetLanguage() {
        MessageSource messageSource = Mockito.mock(MessageSource.class);
        DefaultLocaleMessageProvider provider = new DefaultLocaleMessageProvider(messageSource);

        LocaleContextHolder.setLocale(Locale.SIMPLIFIED_CHINESE);
        String actual = provider.getLanguage();

        Assert.assertEquals(Locale.SIMPLIFIED_CHINESE.toLanguageTag(), actual);
    }

}
