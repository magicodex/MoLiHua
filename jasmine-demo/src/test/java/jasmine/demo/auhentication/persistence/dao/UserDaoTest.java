package jasmine.demo.auhentication.persistence.dao;

import jasmine.demo.authentication.persistence.dao.UserDao;
import jasmine.demo.authentication.persistence.entity.UserEO;
import jasmine.test.context.BeanTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class UserDaoTest extends BeanTestContext {
    @Autowired
    private UserDao userDao;

    @Test
    public void testGetUserByName() {
        UserEO user = userDao.getUserByName("test");

        Assert.assertNotNull(user);
    }

}
