package jasmine.demo.service;

import jasmine.core.context.CurrentSubject;
import jasmine.test.context.BeanTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class UserServiceTest extends BeanTestContext {
    @Autowired
    private UserService userService;

    @Test
    public void testLoadUserByUsername() {
        CurrentSubject.setSubject(1L, null);
        UserDetails actual = userService.loadUserByUsername("test");

        Assert.assertNotNull(actual);
    }

}
