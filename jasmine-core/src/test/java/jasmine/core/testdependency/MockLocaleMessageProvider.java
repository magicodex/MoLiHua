package jasmine.core.testdependency;

import jasmine.core.i18n.LocaleMessageProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mh.z
 */
public class MockLocaleMessageProvider implements LocaleMessageProvider {
    private Map<String, String> messages;

    public MockLocaleMessageProvider() {
        this.messages = new HashMap<>();
    }

    public void addMessage(String messageKey, String message) {
        messages.put(messageKey, message);
    }

    @Override
    public String getMessage(String messageKey, Object... args) {
        String message = null;

        if (messageKey != null) {
            if (messageKey.startsWith("$")) {
                messageKey = messageKey.substring(1);
            }

            message = messages.get(messageKey);

            if (message != null && args.length > 0) {
                message = String.format(message, args);
            }
        }

        return message;
    }

}
