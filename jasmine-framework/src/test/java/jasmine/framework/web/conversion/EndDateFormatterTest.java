package jasmine.framework.web.conversion;

import jasmine.framework.web.annotation.EndDate;
import jasmine.test.mockito.MockUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * @author mh.z
 */
public class EndDateFormatterTest {

    @Test
    public void testParse() throws ParseException {
        EndDate endDate = MockUtil.mock(EndDate.class, (target) -> {
            Mockito.when(target.value()).thenReturn("");
        });

        EndDateFormatter formatter = new EndDateFormatter(endDate, ZonedDateTime.class);
        ZonedDateTime actual = formatter.parse("2020-12-31+08:00", Locale.getDefault());

        Assert.assertNotNull(actual);
    }

    @Test
    public void testPrint() {
        EndDate endDate = MockUtil.mock(EndDate.class, (target) -> {
            Mockito.when(target.value()).thenReturn("");
        });

        EndDateFormatter formatter = new EndDateFormatter(endDate, ZonedDateTime.class);
        String actual = formatter.print(ZonedDateTime.now(), Locale.getDefault());

        Assert.assertNotNull(actual);
    }

}
