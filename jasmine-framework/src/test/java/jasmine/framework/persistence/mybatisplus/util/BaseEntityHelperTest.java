package jasmine.framework.persistence.mybatisplus.util;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.SubjectProvider;
import jasmine.core.i18n.LocaleMessageProvider;
import jasmine.core.util.QI18nUtil;
import jasmine.framework.persistence.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.mock.core.context.MockLocaleMessageProvider;
import jasmine.mock.core.context.MockSubjectProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * @author mh.z
 */
public class BaseEntityHelperTest {
    private LocaleMessageProvider prevLocalMessageProvider;
    private SubjectProvider prevSubjectProvider;

    @Before
    public void setUp() {
        prevLocalMessageProvider = QI18nUtil.getProvider();
        QI18nUtil.initUtil(new MockLocaleMessageProvider());

        prevSubjectProvider = CurrentSubject.getSubjectProvider();
        CurrentSubject.initUtil(new MockSubjectProvider());
    }

    @After
    public void tearDown() {
        QI18nUtil.initUtil(prevLocalMessageProvider);
        CurrentSubject.initUtil(prevSubjectProvider);
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
