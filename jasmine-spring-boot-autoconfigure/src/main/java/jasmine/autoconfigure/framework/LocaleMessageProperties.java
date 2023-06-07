package jasmine.autoconfigure.framework;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.i18n.message")
public class LocaleMessageProperties {

    /**  多语言资源路径 */
    private String[] resourceBaseNames = new String[]{"message/messages"};
    /** 多语言资源编码 */
    private String encoding = "UTF-8";
    /** 多语言常量路径 */
    private String[] constantPatterns = new String[]{"classpath*:/**/constant/*MessageConstants.class"};

    public String[] getResourceBaseNames() {
        return resourceBaseNames;
    }

    public void setResourceBaseNames(String[] resourceBaseNames) {
        this.resourceBaseNames = resourceBaseNames;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String[] getConstantPatterns() {
        return constantPatterns;
    }

    public void setConstantPatterns(String[] constantPatterns) {
        this.constantPatterns = constantPatterns;
    }

}
