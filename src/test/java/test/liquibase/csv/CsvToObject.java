package test.liquibase.csv;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import liquibase.util.csv.opencsv.bean.CsvToBean;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CsvToObject<T> extends CsvToBean<T> {

    @Override
    protected Object convertValue(String value, PropertyDescriptor prop) throws ReflectiveOperationException {
        Object result = null;
        Class<?> type = prop.getPropertyType();

        if (StrUtil.isEmpty(value)) {
            return null;
        }

        if (type == Boolean.class || type == boolean.class) {
            result = toBoolean(value);
        } else if (type == BigDecimal.class) {
            result = toBigDecimal(value);
        } else if (type == ZonedDateTime.class) {
            result = toZonedDateTime(value);
        } else {
            result = super.convertValue(value, prop);
        }

        return result;
    }

    protected Boolean toBoolean(String value) {
        Boolean result = null;

        if ("1".equals(value)) {
            result = Boolean.TRUE;
        } else if ("0".equals(value)) {
            result = Boolean.FALSE;
        }

        return result;
    }

    protected BigDecimal toBigDecimal(String value) {
        BigDecimal result = null;

        if (value != null) {
            result = new BigDecimal(value);
        }

        return result;
    }

    protected ZonedDateTime toZonedDateTime(String value) {
        ZonedDateTime result = null;

        if (value != null) {
            LocalDateTime localDateTime = DateUtil.parseLocalDateTime(value);
            result = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        }

        return result;
    }

}
