package jasmine.example.persistence.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import test.context.BeanTestContext;

@RunWith(SpringRunner.class)
public class ExampleDaoTest extends BeanTestContext {
    @Autowired
    private ExampleDao exampleDao;

    @Test
    public void testListAllExamples() {
        Assert.assertEquals(2, exampleDao.listAllExamples().size());
    }

}
