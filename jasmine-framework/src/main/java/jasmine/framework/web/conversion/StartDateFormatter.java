package jasmine.framework.web.conversion;

import jasmine.core.util.QDateUtil;
import jasmine.framework.web.annotation.StartDate;
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
        return QDateUtil.parseStartYearDay(text);
    }

    @Override
    public String print(ZonedDateTime object, Locale locale) {
        return QDateUtil.formatYearDay(object);
    }

}
