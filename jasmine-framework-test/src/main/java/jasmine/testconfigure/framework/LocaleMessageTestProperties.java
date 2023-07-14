package jasmine.testconfigure.framework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class LocaleMessageTestProperties {
    /**  多语言资源路径 */
    @Value("${jasmine.i18n.message.resourceBaseNames:message/messages}")
    private String[] resourceBaseNames = new String[]{"message/messages"};

    /** 多语言资源编码 */
    @Value("${jasmine.i18n.message.encoding:UTF-8}")
    private String encoding = "UTF-8";

    /** 多语言常量路径 */
    @Value("${jasmine.i18n.message.constantPatterns:classpath*:/jasmine/**/constant/*MessageConstants.class}")
    private String[] constantPatterns;

    public String[] getResourceBaseNames() {
        return resourceBaseNames;
    }

    public String getEncoding() {
        return encoding;
    }

    public String[] getConstantPatterns() {
        return constantPatterns;
    }

}
