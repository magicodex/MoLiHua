package jasmine.framework.web.impl.conversion;

import jasmine.core.util.DateUtil;
import jasmine.framework.web.annotation.conversion.EndDate;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * @author mh.z
 */
public class EndDateFormatter implements Formatter<ZonedDateTime> {
    private EndDate endDate;
    private Class<?> fieldType;

    public EndDateFormatter(EndDate endDate, Class<?> fieldType) {
        this.endDate = endDate;
        this.fieldType = fieldType;
    }

    @Override
    public ZonedDateTime parse(String text, Locale locale) throws ParseException {
        return DateUtil.parseEndYearDay(text);
    }

    @Override
    public String print(ZonedDateTime object, Locale locale) {
        return DateUtil.formatYearDay(object);
    }

}
