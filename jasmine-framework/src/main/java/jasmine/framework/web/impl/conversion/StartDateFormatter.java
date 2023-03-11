package jasmine.framework.web.impl.conversion;

import jasmine.core.util.DateUtil;
import jasmine.framework.web.annotation.conversion.StartDate;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * @author mh.z
 */
public class StartDateFormatter implements Formatter<ZonedDateTime> {
    private StartDate startDate;
    private Class<?> fieldType;

    public StartDateFormatter(StartDate startDate, Class<?> fieldType) {
        this.startDate = startDate;
        this.fieldType = fieldType;
    }

    @Override
    public ZonedDateTime parse(String text, Locale locale) throws ParseException {
        return DateUtil.parseStartYearDay(text);
    }

    @Override
    public String print(ZonedDateTime object, Locale locale) {
        return DateUtil.formatYearDay(object);
    }

}
