package jasmine.core.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class CurrentSubjectTest {
    private static SubjectProvider prevSubjectProvider;

    @Before
    public void setUp() {
        prevSubjectProvider = CurrentSubject.getSubjectProvider();
    }

    @After
    public void tearDown() {
        CurrentSubject.initUtil(prevSubjectProvider);
    }

    @Test
    public void testisInitialized() {
        CurrentSubject.initUtil(null);
        Assert.assertFalse(CurrentSubject.isInitialized());

        CurrentSubject.initUtil(Mockito.mock(SubjectProvider.class));
        Assert.assertTrue(CurrentSubject.isInitialized());
    }

}
