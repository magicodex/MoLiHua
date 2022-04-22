package jasmine.framework.persistence.mybatisplus.i18n.support;

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

    /** SQL列名 */
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VERSION_NUMBER = "version_number";

    public I18nRecord(Map<String, Object> values) {
        this.values = new HashMap<>(values);
    }

    protected Map<String, Object> getValues() {
        return values;
    }

    public Long getId() {
        return getValueAsLong(COLUMN_ID);
    }

    public Integer getVersionNumber() {
        return getValueAsInteger(COLUMN_VERSION_NUMBER);
    }

    public String getValueAsString(String name) {
        return (String) values.get(name);
    }

    public Integer getValueAsInteger(String name) {
        return (Integer) values.get(name);
    }

    public Long getValueAsLong(String name) {
        return (Long) values.get(name);
    }

}
