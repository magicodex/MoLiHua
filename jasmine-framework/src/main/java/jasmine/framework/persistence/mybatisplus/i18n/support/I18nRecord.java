package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.framework.persistence.constant.MapperConstants;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mh.z
 */
public class I18nRecord {
    private Map<String, Object> values;

    public I18nRecord(Map<String, Object> values) {
        this.values = new HashMap<>(values);
    }

    public Long getId() {
        return getValueAsLong(MapperConstants.SQL_COLUMN_ID);
    }

    public Integer getVersionNumber() {
        return getValueAsInteger(MapperConstants.SQL_COLUMN_VERSION_NUMBER);
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

    /**
     *
     * @param records
     * @return
     */
    public static Map<Long, I18nRecord> toI18nRecordMap(Collection<Map> records) {
        QCheckUtil.notNull(records, "records null");

        if (QCollUtil.isEmpty(records)) {
            return Collections.emptyMap();
        }

        Map<Long, I18nRecord> i18nRecordMap = new HashMap<>(records.size());
        records.forEach((record) -> {
            I18nRecord i18nRecord = new I18nRecord(record);
            Long recordId = i18nRecord.getId();

            i18nRecordMap.put(recordId, i18nRecord);
        });

        return i18nRecordMap;
    }

}
