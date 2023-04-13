package jasmine.framework.web.impl.conversion;

import jasmine.framework.web.annotation.conversion.EndDate;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
public class EndDateFormatterFactoryTest {

    @Test
    public void testGetPrinter() {
        EndDate endDate = Mockito.mock(EndDate.class);
        Mockito.when(endDate.value()).thenReturn("");

        EndDateFormatterFactory factory = new EndDateFormatterFactory();
        Printer actual = factory.getPrinter(endDate, ZonedDateTime.class);

        Assert.assertNotNull(actual);
        Assert.assertEquals(EndDateFormatter.class, actual.getClass());
    }

    @Test
    public void testGetParser() {
        EndDate endDate = Mockito.mock(EndDate.class);
        Mockito.when(endDate.value()).thenReturn("");

        EndDateFormatterFactory factory = new EndDateFormatterFactory();
        Parser actual = factory.getParser(endDate, ZonedDateTime.class);

        Assert.assertNotNull(actual);
        Assert.assertEquals(EndDateFormatter.class, actual.getClass());
    }

}
