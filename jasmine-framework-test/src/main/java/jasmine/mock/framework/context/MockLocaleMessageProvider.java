package jasmine.mock.framework.context;

import jasmine.framework.i18n.I18nConstants;
import jasmine.framework.i18n.LocaleMessageProvider;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author mh.z
 */
public class MockLocaleMessageProvider implements LocaleMessageProvider {
    private Map<String, String> messages;
    private String language;

    public MockLocaleMessageProvider() {
        this.messages = new HashMap<>();
        this.language = Locale.SIMPLIFIED_CHINESE.toLanguageTag();
    }

    @Override
    public String getMessage(String messageKey, Object... args) {
        String message = null;

        if (messageKey != null) {
            if (messageKey.startsWith(I18nConstants.I18N_MESSAGE_KEY_PREFIX)) {
                messageKey = messageKey.substring(1);
            }

            message = messages.get(messageKey);
            if (message == null) {
                throw new RuntimeException("not found message '" + messageKey + "'");
            }

            if (args.length > 0) {
                message = MessageFormat.format(message, args);
            }
        }

        return message;
    }

    @Override
    public String getMessageAllowNone(String messageKey, Object... args) {
        String message = null;

        if (messageKey != null) {
            if (messageKey.startsWith(I18nConstants.I18N_MESSAGE_KEY_PREFIX)) {
                messageKey = messageKey.substring(1);
            }

            message = messages.get(messageKey);

            if (message != null && args.length > 0) {
                message = MessageFormat.format(message, args);
            }
        }

        return message;
    }

    public void addMessage(String messageKey, String message) {
        messages.put(messageKey, message);
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
