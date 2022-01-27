package jasmine.core.util;

import jasmine.core.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

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
    public void testToMap() {
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
    public void testToList() {
        {
            Collection<Example1> exampleList = new LinkedHashSet<>();
            exampleList.add(Example1.create("1", 1, true));
            exampleList.add(Example1.create("2", 2, false));

            List<Example1> actualList = QCollectionUtil.toList(exampleList);
            Assert.assertEquals(2, actualList.size());
            Assert.assertEquals("1", actualList.get(0).getString1());
            Assert.assertEquals("2", actualList.get(1).getString1());
        }

        // 集合为空的情况
        {
            Collection<Example1> exampleList = null;
            List<Example1> actualList = QCollectionUtil.toList(exampleList);

            Assert.assertTrue(actualList instanceof List);
            Assert.assertEquals(0, actualList.size());
        }
    }

    @Test
    public void testFirst() {
        {
            List<Example1> exampleList = new ArrayList<>();
            exampleList.add(Example1.create("1", 1, true));
            exampleList.add(Example1.create("2", 2, false));

            Example1 actual = QCollectionUtil.first(exampleList);
            Assert.assertNotNull(actual);
            Assert.assertEquals("1", actual.getString1());
        }

        // 集合为空的情况
        {
            Example1 actual = QCollectionUtil.first(null);
            Assert.assertNull(actual);
        }
    }

    @Test
    public void testGroupingBy() {
        {
            List<Example1> exampleList = new ArrayList<>();
            exampleList.add(Example1.create("1", 1, true));
            exampleList.add(Example1.create("2", 2, false));
            exampleList.add(Example1.create("3", 3, true));

            Map<Boolean, List<Example1>> actualMap = QCollectionUtil
                    .groupingBy(exampleList, Example1::getBoolean1);
            Assert.assertEquals(2, actualMap.size());
            Assert.assertEquals(2, actualMap.get(true).size());
            Assert.assertEquals(1, actualMap.get(false).size());
        }

        // 集合为空的情况
        {
            Map<Boolean, List<Example1>> actualMap = QCollectionUtil
                    .groupingBy(null, Example1::getBoolean1);
            Assert.assertTrue(actualMap instanceof Map);
            Assert.assertEquals(0, actualMap.size());
        }
    }

}
