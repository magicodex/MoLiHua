package jasmine.framework.database.mybatisplus.util;

import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.SubjectProvider;
import jasmine.framework.i18n.LocaleMessageProvider;
import jasmine.framework.common.util.I18nUtil;
import jasmine.framework.database.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.framework.testdependency.mock.MockLocaleMessageProvider;
import jasmine.framework.testdependency.mock.MockSubjectProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * @author mh.z
 */
public class BaseEntityUtilTest {
    private LocaleMessageProvider prevLocalMessageProvider;
    private SubjectProvider prevSubjectProvider;

    @Before
    public void setUp() {
        prevLocalMessageProvider = I18nUtil.getProvider();
        I18nUtil.initUtil(new MockLocaleMessageProvider());

        prevSubjectProvider = CurrentSubject.getSubjectProvider();
        CurrentSubject.initUtil(new MockSubjectProvider());
    }

    @After
    public void tearDown() {
        I18nUtil.initUtil(prevLocalMessageProvider);
        CurrentSubject.initUtil(prevSubjectProvider);
    }

    @Test
    public void testFillInsert() {
        TestEntity1 entity = new TestEntity1();
        BaseEntityUtil.fillInsert(entity);

        Assert.assertNotNull(entity.getCreatedBy());
        Assert.assertNotNull(entity.getCreatedDate());
        Assert.assertNotNull(entity.getLastUpdatedBy());
        Assert.assertNotNull(entity.getLastUpdatedDate());
        Assert.assertNotNull(entity.getCreatedLang());
    }

    @Test
    public void testFillInsert2() {
        TestEntity1 entity = new TestEntity1();
        BaseEntityUtil.fillInsert(Collections.singletonList(entity));

        Assert.assertNotNull(entity.getCreatedBy());
        Assert.assertNotNull(entity.getCreatedDate());
        Assert.assertNotNull(entity.getLastUpdatedBy());
        Assert.assertNotNull(entity.getLastUpdatedDate());
        Assert.assertNotNull(entity.getCreatedLang());
    }

    @Test
    public void testFillUpdate() {
        TestEntity1 entity = new TestEntity1();
        BaseEntityUtil.fillUpdate(entity);

        Assert.assertNotNull(entity.getLastUpdatedBy());
        Assert.assertNotNull(entity.getLastUpdatedDate());
    }

    @Test
    public void testFillUpdate2() {
        TestEntity1 entity = new TestEntity1();
        BaseEntityUtil.fillUpdate(Collections.singletonList(entity));

        Assert.assertNotNull(entity.getLastUpdatedBy());
        Assert.assertNotNull(entity.getLastUpdatedDate());
    }

}
