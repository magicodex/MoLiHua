package jasmine.framework.database.mybatisplus.util;

import cn.hutool.core.collection.CollStreamUtil;
import jasmine.framework.database.mybatisplus.testdependency.context.MybatisTestContext;
import jasmine.framework.database.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.framework.database.mybatisplus.testdependency.mapper.TestEntity1Mapper;
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
public class MapperExtensionUtilTest extends MybatisTestContext {
    @Autowired
    private TestEntity1Mapper testEntity1Mapper;

    @Test
    public void testSaveBatch() {
        List<TestEntity1> list = new ArrayList<>();
        list.add(createEntity("code1", "name1", "attr1"));
        list.add(createEntity("code2", "name2", "attr2"));

        MapperExtensionUtil.saveBatch(testEntity1Mapper, list);
    }

    @Test
    public void testUpdateById() {
        TestEntity1 entity = createEntity("code1", "name1", "attr1");
        testEntity1Mapper.insert(entity);
        Integer versionNumber = entity.getVersionNumber();

        // 版本不对无法更新
        Assert.assertThrows(RuntimeException.class, () -> {
            entity.setVersionNumber(versionNumber + 1);
            MapperExtensionUtil.updateById(testEntity1Mapper, entity, true);
        });

        // 版本正确可以更新
        {
            entity.setVersionNumber(versionNumber);
            MapperExtensionUtil.updateById(testEntity1Mapper, entity, true);
        }
    }

    @Test
    public void testUpdateBatchById() {
        TestEntity1 entity1 = createEntity("code1", "name1", "attr1");
        testEntity1Mapper.insert(entity1);
        TestEntity1 entity2 = createEntity("code2", "name2", "attr2");
        testEntity1Mapper.insert(entity2);
        Integer versionNumber2 = entity2.getVersionNumber();

        // 部分版本不对无法更新
        Assert.assertThrows(RuntimeException.class, () -> {
            entity2.setVersionNumber(versionNumber2 + 1);
            MapperExtensionUtil.updateBatchById(testEntity1Mapper, Arrays.asList(entity1, entity2), true);
        });

        // 全部版本正确才能更新
        {
            entity2.setVersionNumber(versionNumber2);
            MapperExtensionUtil.updateBatchById(testEntity1Mapper, Arrays.asList(entity1, entity2), true);
        }
    }

    @Test
    public void testDeleteById() {
        TestEntity1 entity1 = createEntity("code1", "name1", "attr1");
        testEntity1Mapper.insert(entity1);

        // 删除不存在的记录会报错
        Assert.assertThrows(RuntimeException.class, () -> {
            MapperExtensionUtil.deleteById(testEntity1Mapper, -1, true);
        });

        // 删除存在的记录
        {
            MapperExtensionUtil.deleteById(testEntity1Mapper, entity1.getId(), true);
        }
    }

    @Test
    public void testDeleteByIds() {
        TestEntity1 entity1 = createEntity("code1", "name1", "attr1");
        testEntity1Mapper.insert(entity1);
        TestEntity1 entity2 = createEntity("code2", "name2", "attr2");
        testEntity1Mapper.insert(entity2);
        TestEntity1 entity3 = createEntity("code2", "name2", "attr2");
        testEntity1Mapper.insert(entity3);

        // 删除不存在的记录会报错
        Assert.assertThrows(RuntimeException.class, () -> {
            List<Long> idList = Arrays.asList(-1L, entity1.getId());
            MapperExtensionUtil.deleteByIds(testEntity1Mapper, idList, true);
        });

        // 删除的所有记录都存在
        {
            List<Long> idList = Arrays.asList(entity2.getId(), entity3.getId());
            MapperExtensionUtil.deleteByIds(testEntity1Mapper, idList, true);
        }
    }

    @Test
    public void testListByIds() {
        List<TestEntity1> list = new ArrayList<>();
        list.add(createEntity("code1", "name1", "attr1"));
        list.add(createEntity("code2", "name2", "attr2"));
        MapperExtensionUtil.saveBatch(testEntity1Mapper, list);

        // 批量查询记录
        List<Long> idList = CollStreamUtil.toList(list, TestEntity1::getId);
        List<TestEntity1> entityList = MapperExtensionUtil.listByIds(testEntity1Mapper, idList, true);

        Assert.assertEquals(2, entityList.size());
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
