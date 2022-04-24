package jasmine.framework.persistence.mybatisplus.util;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QI18nUtil;
import jasmine.framework.persistence.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.mock.context.MockLocaleMessageProvider;
import jasmine.mock.context.MockSubjectProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * @author mh.z
 */
public class BaseEntityHelperTest {

    @Before
    public void beforeTestCase() {
        QI18nUtil.initUtil(new MockLocaleMessageProvider());
        CurrentSubject.initUtil(new MockSubjectProvider());
    }

    @Test
    public void testFillInsert() {
        TestEntity1 entity = new TestEntity1();
        BaseEntityHelper.fillInsert(entity);

        Assert.assertNotNull(entity.getCreatedBy());
        Assert.assertNotNull(entity.getCreatedDate());
        Assert.assertNotNull(entity.getLastUpdatedBy());
        Assert.assertNotNull(entity.getLastUpdatedDate());
        Assert.assertNotNull(entity.getCreatedLang());
    }

    @Test
    public void testFillInsert2() {
        TestEntity1 entity = new TestEntity1();
        BaseEntityHelper.fillInsert(Collections.singletonList(entity));

        Assert.assertNotNull(entity.getCreatedBy());
        Assert.assertNotNull(entity.getCreatedDate());
        Assert.assertNotNull(entity.getLastUpdatedBy());
        Assert.assertNotNull(entity.getLastUpdatedDate());
        Assert.assertNotNull(entity.getCreatedLang());
    }

    @Test
    public void testFillUpdate() {
        TestEntity1 entity = new TestEntity1();
        BaseEntityHelper.fillUpdate(entity);

        Assert.assertNotNull(entity.getLastUpdatedBy());
        Assert.assertNotNull(entity.getLastUpdatedDate());
    }

    @Test
    public void testFillUpdate2() {
        TestEntity1 entity = new TestEntity1();
        BaseEntityHelper.fillUpdate(Collections.singletonList(entity));

        Assert.assertNotNull(entity.getLastUpdatedBy());
        Assert.assertNotNull(entity.getLastUpdatedDate());
    }

}
