package jasmine.core.util;

import jasmine.core.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mh.z
 */
public class QCollectionUtilTest {

    @Test
    public void testMapToList() {
        {
            List<Example1> exampleList = new ArrayList<>();
            exampleList.add(Example1.create("1", 1, true));
            exampleList.add(Example1.create("2", 2, false));

            List<String> actualList = QCollectionUtil.mapToList(exampleList, Example1::getString1);
            Assert.assertEquals(2, actualList.size());
            Assert.assertEquals("1", actualList.get(0));
            Assert.assertEquals("2", actualList.get(1));
        }

        // 集合为空的情况
        {
            List<String> actualList = QCollectionUtil.mapToList(null, Example1::getString1);
            Assert.assertTrue(actualList instanceof List);
            Assert.assertEquals(0, actualList.size());
        }
    }

    @Test
    public void testChooseToList() {
        {
            List<Example1> exampleList = new ArrayList<>();
            exampleList.add(Example1.create("1", 1, true));
            exampleList.add(Example1.create("2", 2, false));
            exampleList.add(Example1.create("3", 3, true));

            List<Example1> actualList = QCollectionUtil.chooseToList(exampleList, (item) -> {
                return Boolean.TRUE.equals(item.getBoolean1());
            });

            Assert.assertEquals(2, actualList.size());
            Assert.assertEquals("1", actualList.get(0).getString1());
            Assert.assertEquals("3", actualList.get(1).getString1());
        }

        // 集合为空的情况
        {
            List<Example1> actualList = QCollectionUtil.chooseToList(null, (item) -> {
                return Boolean.TRUE.equals(item.getBoolean1());
            });

            Assert.assertTrue(actualList instanceof List);
            Assert.assertEquals(0, actualList.size());
        }
    }

    @Test
    public void testCastToList() {
        {
            Collection<?> collection = QCollectionUtil.castToList(new ArrayList<>());
            Assert.assertTrue(collection instanceof List);
        }

        {
            Collection<?> collection = QCollectionUtil.castToList(Set.of("value1", "value2"));
            Assert.assertTrue(collection instanceof List);
        }
    }

    @Test
    public void testToMap1() {
        List<Example1> exampleList = new ArrayList<>();
        exampleList.add(Example1.create("1", 1, true));
        exampleList.add(Example1.create("2", 2, false));

        Map<String, Example1> actualMap = QCollectionUtil.toMap(exampleList, Example1::getString1);
        Assert.assertEquals(2, actualMap.size());
        Assert.assertEquals(Integer.valueOf(1), actualMap.get("1").getInteger1());
        Assert.assertEquals(Integer.valueOf(2), actualMap.get("2").getInteger1());
    }

    @Test
    public void testToMap2() {
        {
            List<Example1> exampleList = new ArrayList<>();
            exampleList.add(Example1.create("1", 1, true));
            exampleList.add(Example1.create("2", 2, false));

            Map<String, Integer> actualMap = QCollectionUtil.toMap(exampleList,
                    Example1::getString1, Example1::getInteger1);
            Assert.assertEquals(2, actualMap.size());
            Assert.assertEquals(Integer.valueOf(1), actualMap.get("1"));
            Assert.assertEquals(Integer.valueOf(2), actualMap.get("2"));
        }

        // 集合为空的情况
        {
            Map<String, Integer> actualMap = QCollectionUtil.toMap(null,
                    Example1::getString1, Example1::getInteger1);
            Assert.assertTrue(actualMap instanceof Map);
            Assert.assertEquals(0, actualMap.size());
        }
    }

    @Test
    public void testGroupBy() {
        List<Example1> exampleList = new ArrayList<>();
        exampleList.add(Example1.create("1", 1, true));
        exampleList.add(Example1.create("2", 2, false));
        exampleList.add(Example1.create("3", 1, false));

        Map<Integer, List<Example1>> actualMap = QCollectionUtil.groupBy(exampleList, Example1::getInteger1);
        Assert.assertEquals(2, actualMap.size());
        Assert.assertEquals(2, actualMap.get(1).size());
        Assert.assertEquals(1, actualMap.get(2).size());
    }

    @Test
    public void testForEach1() {
        List<Example1> exampleList = new ArrayList<>();
        exampleList.add(Example1.create("1", 1, true));
        exampleList.add(Example1.create("2", 2, false));

        List<Integer> actualList = QCollectionUtil.forEach(exampleList, Example1::getInteger1);
        Assert.assertEquals(2, actualList.size());
        Assert.assertEquals(Integer.valueOf(1), actualList.get(0));
        Assert.assertEquals(Integer.valueOf(2), actualList.get(1));
    }

    @Test
    public void testForEach2() {
        List<Example1> exampleList = new ArrayList<>();
        exampleList.add(Example1.create("1", 1, true));
        exampleList.add(Example1.create("2", 2, false));

        QCollectionUtil.forEach(exampleList, (example) -> {
            example.setInteger1(example.getInteger1() + 1000);
        });

        Assert.assertEquals(Integer.valueOf(1001), exampleList.get(0).getInteger1());
        Assert.assertEquals(Integer.valueOf(1002), exampleList.get(1).getInteger1());
    }

}
