package jasmine.framework.web.impl.conversion;

import jasmine.framework.web.annotation.conversion.StartDate;
import jasmine.core.test.mockito.MockUtil;
import jasmine.framework.web.impl.conversion.StartDateFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * @author mh.z
 */
public class StartDateFormatterTest {

    @Test
    public void testParse() throws ParseException {
        StartDate startDate = MockUtil.mock(StartDate.class, (target) -> {
            Mockito.when(target.value()).thenReturn("");
        });

        StartDateFormatter formatter = new StartDateFormatter(startDate, ZonedDateTime.class);
        ZonedDateTime actual = formatter.parse("2020-12-31+08:00", Locale.getDefault());

        Assert.assertNotNull(actual);
    }

    @Test
    public void testPrint() {
        StartDate startDate = MockUtil.mock(StartDate.class, (target) -> {
            Mockito.when(target.value()).thenReturn("");
        });

        StartDateFormatter formatter = new StartDateFormatter(startDate, ZonedDateTime.class);
        String actual = formatter.print(ZonedDateTime.now(), Locale.getDefault());

        Assert.assertNotNull(actual);
    }

}
