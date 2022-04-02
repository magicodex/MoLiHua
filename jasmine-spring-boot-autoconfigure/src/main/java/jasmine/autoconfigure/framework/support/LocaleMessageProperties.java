package jasmine.autoconfigure.framework.support;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.i18n.message")
public class LocaleMessageProperties {

    /**  多语言资源路径 */
    private String resourcePrefix = "message/messages";
    /** 多语言资源编码 */
    private String encoding = "UTF-8";
    /** 多语言常量路径 */
    private String constantPattern = "classpath*:/**/constant/*Messages.class";

    public String getResourcePrefix() {
        return resourcePrefix;
    }

    public void setResourcePrefix(String resourcePrefix) {
        this.resourcePrefix = resourcePrefix;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getConstantPattern() {
        return constantPattern;
    }

    public void setConstantPattern(String constantPattern) {
        this.constantPattern = constantPattern;
    }

}
