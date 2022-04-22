package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 多语言记录。
 * </p>
 *
 * @author mh.z
 */
public class I18nRecord {
    /** 列名与值的映射集 */
    private Map<String, Object> values;

    /** 列名 */
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VERSION_NUMBER = "version_number";
    private static final String COLUMN_LANG_CODE = "lang_code";
    private static final String COLUMN_DEFAULT_FLAG = "default_flag";

    public I18nRecord(Map<String, Object> values) {
        this.values = new HashMap<>(values);
    }

    protected Map<String, Object> getValues() {
        return values;
    }

    public String getValueAsString(String name) {
        return QStringUtil.toString(values.get(name));
    }

    public Integer getValueAsInteger(String name) {
        return QObjectUtil.parseInteger(values.get(name));
    }

    public Long getValueAsLong(String name) {
        return QObjectUtil.parseLong(values.get(name));
    }

    public Boolean getValueAsBoolean(String name) {
        return QObjectUtil.parseBoolean(values.get(name));
    }

    public Long getId() {
        return getValueAsLong(COLUMN_ID);
    }

    public Integer getVersionNumber() {
        return getValueAsInteger(COLUMN_VERSION_NUMBER);
    }

    public String getLangCode() {
        return getValueAsString(COLUMN_LANG_CODE);
    }

    public Boolean getDefaultFlag() {
        return getValueAsBoolean(COLUMN_DEFAULT_FLAG);
    }

}
