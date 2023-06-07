package jasmine.framework.i18n.builder;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.i18n.DeclareI18nScanUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Properties;

/**
 * @author mh.z
 */
public class MessageSourceBuilder {
    private String[] resourceBaseNames;
    private String encoding;
    private String[] constantPatterns;

    /**  多语言资源路径 */
    private static final String DEFAULT_MESSAGE_BASE_NAME = "message/messages";
    /** 多语言资源编码 */
    private static final String DEFAULT_MESSAGE_DEFAULT_ENCODING = "UTF-8";
    /** 多语言常量路径 */
    private static final String DEFAULT_MESSAGE_CONSTANT_PATH = "classpath*:/jasmine/**/constant/*MessageConstants.class";

    public MessageSourceBuilder() {
        resourceBaseNames = new String[]{DEFAULT_MESSAGE_BASE_NAME};
        encoding = DEFAULT_MESSAGE_DEFAULT_ENCODING;
        constantPatterns = new String[]{DEFAULT_MESSAGE_CONSTANT_PATH};
    }

    public void setResourceBaseNames(String... resourceBaseNames) {
        this.resourceBaseNames = resourceBaseNames;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setConstantPatterns(String... constantPatterns) {
        this.constantPatterns = constantPatterns;
    }

    /**
     * 创建
     *
     * @return
     */
    public MessageSource build() {
        CheckUtil.notNullProp(resourceBaseNames, "resourceBaseNames null");
        CheckUtil.notNullProp(encoding, "encoding null");
        CheckUtil.notNullProp(constantPatterns, "constantPatterns null");

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(resourceBaseNames);
        messageSource.setDefaultEncoding(encoding);
        // 扫描多语言常量
        Properties commonMessages = DeclareI18nScanUtil.scan(constantPatterns);
        messageSource.setCommonMessages(commonMessages);

        return messageSource;
    }

}
