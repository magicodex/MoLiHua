package jasmine.framework.common.util.csv;

import au.com.bytecode.opencsv.bean.CsvToBean;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import jasmine.framework.common.util.NewUtil;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 转换 CSV 成对象。
 * </p>
 *
 * @author mh.z
 */
public class CsvToObject<T> extends CsvToBean<T> {
    private Map<Class<?>, Function> valueConverters;

    private static final String TRUE_TEXT = "true";
    private static final String FALSE_TEXT = "false";
    private static final String TRUE_VALUE = "1";
    private static final String FALSE_VALUE = "0";

    public CsvToObject() {
        this.valueConverters = NewUtil.map();
    }

    public CsvToObject(Map<Class<?>, Function> valueConverters) {
        this.valueConverters = valueConverters;
    }

    public <R> Function<String, R> getValueConverter(Class<R> clazz) {
        return valueConverters.get(clazz);
    }

    public Map<Class<?>, Function> getValueConverters() {
        return valueConverters;
    }

    public <R> void addValueConverter(Class<R> clazz, Function<String, R> newConverter) {
        valueConverters.put(clazz, newConverter);
    }

    public <R> void addValueConverters(Map<Class<?>, Function> newConverters) {
        valueConverters.putAll(newConverters);
    }

    @Override
    protected Object convertValue(String value, PropertyDescriptor prop)
            throws InstantiationException, IllegalAccessException {
        Object result = null;
        Class<?> type = prop.getPropertyType();

        if (StrUtil.isEmpty(value)) {
            return null;
        }

        Class<?> valueType = value.getClass();
        Function valueConverter = valueConverters.get(valueType);

        if (valueConverter != null) {
            return valueConverter.apply(value);
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

    /**
     * 转换成 Boolean 对象
     *
     * @param value
     * @return
     */
    protected Boolean toBoolean(String value) {
        Boolean result = null;

        if (TRUE_VALUE.equals(value) || TRUE_TEXT.equals(value)) {
            result = Boolean.TRUE;
        } else if (FALSE_VALUE.equals(value) || FALSE_TEXT.equals(value)) {
            result = Boolean.FALSE;
        }

        return result;
    }

    /**
     * 转换成 Decimal 对象
     *
     * @param value
     * @return
     */
    protected BigDecimal toBigDecimal(String value) {
        BigDecimal result = null;

        if (value != null) {
            result = new BigDecimal(value);
        }

        return result;
    }

    /**
     * 转换成 ZonedDateTime 对象
     *
     * @param value
     * @return
     */
    protected ZonedDateTime toZonedDateTime(String value) {
        ZonedDateTime result = null;

        if (value != null) {
            LocalDateTime localDateTime = DateUtil.parseLocalDateTime(value);
            result = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        }

        return result;
    }

}
