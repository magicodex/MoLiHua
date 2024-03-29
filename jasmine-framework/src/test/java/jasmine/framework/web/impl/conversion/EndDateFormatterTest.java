package jasmine.framework.web.impl.conversion;

import jasmine.framework.web.annotation.conversion.EndDate;
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
        EndDate endDate = Mockito.mock(EndDate.class);
        Mockito.when(endDate.value()).thenReturn("");

        EndDateFormatter formatter = new EndDateFormatter(endDate, ZonedDateTime.class);
        ZonedDateTime actual = formatter.parse("2020-12-31+08:00", Locale.getDefault());

        Assert.assertNotNull(actual);
    }

    @Test
    public void testPrint() {
        EndDate endDate = Mockito.mock(EndDate.class);
        Mockito.when(endDate.value()).thenReturn("");

        EndDateFormatter formatter = new EndDateFormatter(endDate, ZonedDateTime.class);
        String actual = formatter.print(ZonedDateTime.now(), Locale.getDefault());

        Assert.assertNotNull(actual);
    }

}
