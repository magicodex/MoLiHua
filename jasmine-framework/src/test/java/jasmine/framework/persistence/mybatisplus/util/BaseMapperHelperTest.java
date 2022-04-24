package jasmine.framework.persistence.mybatisplus.util;

import jasmine.framework.persistence.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.framework.persistence.mybatisplus.testdependency.mapper.TestEntity1Mapper;
import jasmine.framework.testdependency.context.FrameworkTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class BaseMapperHelperTest extends FrameworkTestContext {
    @Autowired
    private TestEntity1Mapper testEntity1Mapper;

    @Test
    public void testSaveBatch() {
        List<TestEntity1> list = new ArrayList<>();
        list.add(createEntity("code1", "name1", "attr1"));
        list.add(createEntity("code2", "name2", "attr2"));

        BaseMapperHelper.saveBatch(testEntity1Mapper, list);
    }

    @Test
    public void testStrictUpdateById() {
        TestEntity1 entity = createEntity("code1", "name1", "attr1");
        testEntity1Mapper.insert(entity);
        Integer versionNumber = entity.getVersionNumber();

        // 版本不对无法更新
        Assert.assertThrows(RuntimeException.class, () -> {
            entity.setVersionNumber(versionNumber + 1);
            BaseMapperHelper.strictUpdateById(testEntity1Mapper, entity);
        });

        // 版本正确可以更新
        {
            entity.setVersionNumber(versionNumber);
            BaseMapperHelper.strictUpdateById(testEntity1Mapper, entity);
        }
    }

    @Test
    public void testStrictUpdateBatchById() {
        TestEntity1 entity1 = createEntity("code1", "name1", "attr1");
        testEntity1Mapper.insert(entity1);
        TestEntity1 entity2 = createEntity("code2", "name2", "attr2");
        testEntity1Mapper.insert(entity2);
        Integer versionNumber2 = entity2.getVersionNumber();

        // 部分版本不对无法更新
        Assert.assertThrows(RuntimeException.class, () -> {
            entity2.setVersionNumber(versionNumber2 + 1);
            BaseMapperHelper.strictUpdateBatchById(testEntity1Mapper, Arrays.asList(entity1, entity2));
        });

        // 全部版本正确才能更新
        {
            entity2.setVersionNumber(versionNumber2);
            BaseMapperHelper.strictUpdateBatchById(testEntity1Mapper, Arrays.asList(entity1, entity2));
        }
    }

    @Test
    public void testStrictDeleteById() {
        TestEntity1 entity1 = createEntity("code1", "name1", "attr1");
        testEntity1Mapper.insert(entity1);

        // 删除不存在的记录会报错
        Assert.assertThrows(RuntimeException.class, () -> {
            BaseMapperHelper.strictDeleteById(testEntity1Mapper, -1);
        });

        // 删除存在的记录
        {
            BaseMapperHelper.strictDeleteById(testEntity1Mapper, entity1.getId());
        }
    }

    @Test
    public void testStrictDeleteByIds() {
        TestEntity1 entity1 = createEntity("code1", "name1", "attr1");
        testEntity1Mapper.insert(entity1);
        TestEntity1 entity2 = createEntity("code2", "name2", "attr2");
        testEntity1Mapper.insert(entity2);
        TestEntity1 entity3 = createEntity("code2", "name2", "attr2");
        testEntity1Mapper.insert(entity3);

        // 删除不存在的记录会报错
        Assert.assertThrows(RuntimeException.class, () -> {
            List<Long> idList = Arrays.asList(-1L, entity1.getId());
            BaseMapperHelper.strictDeleteByIds(testEntity1Mapper, idList);
        });

        // 删除的所有记录都存在
        {
            List<Long> idList = Arrays.asList(entity2.getId(), entity3.getId());
            BaseMapperHelper.strictDeleteByIds(testEntity1Mapper, idList);
        }
    }

    /**
     * 创建 TestEntity1 对象
     *
     * @param code
     * @param name
     * @param attr1
     * @return
     */
    private TestEntity1 createEntity(String code, String name, String attr1) {
        TestEntity1 entity = new TestEntity1();

        entity.setCode1(code);
        entity.setName1(name);
        entity.setAttr1(attr1);

        return entity;
    }

}
