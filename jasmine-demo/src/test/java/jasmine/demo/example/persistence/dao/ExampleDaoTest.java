package jasmine.demo.example.persistence.dao;

import jasmine.demo.example.persistence.dao.ExampleDao;
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
public class ExampleDaoTest extends BeanTestContext {
    @Autowired
    private ExampleDao exampleDao;

    @Test
    public void testListAllExamples() {
        Assert.assertEquals(2, exampleDao.listAllExamples().size());
    }

}
