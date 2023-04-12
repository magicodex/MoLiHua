package jasmine.framework.database.mybatisplus.i18n.support;

import jasmine.core.util.ObjectUtil;
import jasmine.core.util.StringUtil;

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
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_VERSION_NUMBER = "version_number";
    protected static final String COLUMN_LANG_CODE = "lang_code";
    protected static final String COLUMN_DEFAULT_FLAG = "default_flag";

    public I18nRecord(Map<String, Object> values) {
        this.values = new HashMap<>(values);
    }

    protected Map<String, Object> getValues() {
        return values;
    }

    public String getValueAsString(String name) {
        return StringUtil.toString(values.get(name));
    }

    public Integer getValueAsInteger(String name) {
        return ObjectUtil.parseInteger(values.get(name));
    }

    public Long getValueAsLong(String name) {
        return ObjectUtil.parseLong(values.get(name));
    }

    public Boolean getValueAsBoolean(String name) {
        Object value = values.get(name);

        if (value == null) {
            return null;
        }

        return ("1".equals(value.toString()))
                ? true : false;
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
