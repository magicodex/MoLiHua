package jasmine.framework.web.impl.conversion;

import jasmine.framework.web.annotation.conversion.StartDate;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
public class StartDateFormatterFactoryTest {

    @Test
    public void testGetPrinter() {
        StartDate startDate = Mockito.mock(StartDate.class);
        Mockito.when(startDate.value()).thenReturn("");

        StartDateFormatterFactory factory = new StartDateFormatterFactory();
        Printer actual = factory.getPrinter(startDate, ZonedDateTime.class);

        Assert.assertNotNull(actual);
        Assert.assertEquals(StartDateFormatter.class, actual.getClass());
    }

    @Test
    public void testGetParser() {
        StartDate startDate = Mockito.mock(StartDate.class);
        Mockito.when(startDate.value()).thenReturn("");

        StartDateFormatterFactory factory = new StartDateFormatterFactory();
        Parser actual = factory.getParser(startDate, ZonedDateTime.class);

        Assert.assertNotNull(actual);
        Assert.assertEquals(StartDateFormatter.class, actual.getClass());
    }

}
