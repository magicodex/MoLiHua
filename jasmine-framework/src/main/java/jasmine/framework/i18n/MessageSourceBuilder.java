package jasmine.framework.i18n;

import jasmine.core.util.CheckUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Properties;

/**
 * @author mh.z
 */
public class MessageSourceBuilder {
    private String resourcePrefix;
    private String encoding;
    private String constantPattern;

    /**  多语言资源路径 */
    private static final String DEFAULT_MESSAGE_PATH_PREFIX = "message/messages";
    /** 多语言资源编码 */
    private static final String DEFAULT_MESSAGE_DEFAULT_ENCODING = "UTF-8";
    /** 多语言常量路径 */
    private static final String DEFAULT_MESSAGE_CONSTANT_PATH = "classpath*:/**/constant/*Messages.class";

    public MessageSourceBuilder() {
        resourcePrefix = DEFAULT_MESSAGE_PATH_PREFIX;
        encoding = DEFAULT_MESSAGE_DEFAULT_ENCODING;
        constantPattern = DEFAULT_MESSAGE_CONSTANT_PATH;
    }

    public void setResourcePrefix(String resourcePrefix) {
        this.resourcePrefix = resourcePrefix;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setConstantPattern(String constantPattern) {
        this.constantPattern = constantPattern;
    }

    /**
     * 创建
     *
     * @return
     */
    public MessageSource build() {
        CheckUtil.notNullProp(resourcePrefix, "resourcePrefix null");
        CheckUtil.notNullProp(encoding, "encoding null");
        CheckUtil.notNullProp(constantPattern, "constantPattern null");

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(resourcePrefix);
        messageSource.setDefaultEncoding(encoding);
        // 扫描多语言常量
        Properties commonMessages = DeclareI18nScanUtil.scan(constantPattern);
        messageSource.setCommonMessages(commonMessages);

        return messageSource;
    }

}
