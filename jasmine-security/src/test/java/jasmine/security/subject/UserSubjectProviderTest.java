package jasmine.security.subject;

import jasmine.test.mockito.MockUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class UserSubjectProviderTest {

    @Test
    public void test() {
        UserSubjectDetailsService service = MockUtil.mock(UserSubjectDetailsService.class, (target) -> {
            Mockito.when(target.loadUserByUserId(Mockito.anyLong()))
                    .thenAnswer((invocationOnMock) -> {
                        Long userId = invocationOnMock.getArgument(0);

                        return new UserSubject(100001L, userId);
                    });
        });

        UserSubjectProvider provider = new UserSubjectProvider(service);
        provider.setCurrentSubject(null, 100100L);

        Assert.assertEquals(Long.valueOf(100001L), provider.getCurrentTenantId());
        Assert.assertEquals(Long.valueOf(100100L), provider.getCurrentUserId());
    }

}
