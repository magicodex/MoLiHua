package jasmine.framework.web.util.routing;

import java.util.Collection;
import java.util.Map;

import jasmine.framework.web.util.routing.testdependency.Sample;
import jasmine.framework.web.util.routing.testdependency.SampleGroup;
import jasmine.framework.web.util.routing.testdependency.SampleUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class RouterTest {
    private static final String SAMPLE01 = "/test/framework/web/Router_1.json";

    public static void main(String[] args) {
        Router routes = new Router();

        // 添加一条路径到路由表中，其中冒号开头的代表路径参数
        routes.addRoute("/zoos/:zooId/animals/:animalId", "去动物园看动物");

        // 在路由表里匹配具体的路径
        Route route = routes.getRoute("/zoos/BeijingZoo/animals/panda");
        System.out.println(route.getBindingValue()); // => "去动物园看动物"

        // 获取路径参数
        Map<String, String> pathParameterMap = route.getPathParameterMap();
        System.out.println(pathParameterMap.get("zooId")); // => BeijingZoo
        System.out.println(pathParameterMap.get("animalId")); // => panda
    }

    @Test
    public void testGetPathSegments() {
        Router routes = new Router();

        Assert.assertArrayEquals(new String[]{"", "1", "2", "3"}, routes.getPathSegments("/1/2/3"));
        Assert.assertArrayEquals(new String[]{"", "1", ":2", "3"}, routes.getPathSegments("/1/:2/3"));
        Assert.assertArrayEquals(new String[]{"", "1", "", "3"}, routes.getPathSegments("/1//3"));
    }

    @Test
    public void testGetPathParameterName() {
        Router routes = new Router();

        Assert.assertEquals(null, routes.getPathParameterName("1"));
        Assert.assertEquals("2", routes.getPathParameterName(":2"));
        Assert.assertEquals(null, routes.getPathParameterName(""));
    }

    @Test
    public void test() {
        Router routes = new Router();
        // 加载样例
        Collection<SampleGroup> groups = SampleUtil.loadSampleGroupsFromClasspath(SAMPLE01);

        // 添加路径
        for (SampleGroup group : groups) {
            routes.addRoute(group.getPath(), group.getPath());
        }

        // 检查路径
        for (SampleGroup group : groups) {
            for (Sample sample : group.getSamples()) {
                checkPath(routes, group, sample);
            }
        }
    }

    /**
     * 检查路径
     *
     * @param routes 路由表
     * @param group 样例组
     * @param sample 样例
     */
    private void checkPath(Router routes, SampleGroup group, Sample sample) {
        String path = sample.getKey();
        Route route = routes.getRoute(path);
        // 应该匹配到路由
        Assert.assertNotNull(route);

        String expectedPath = group.getPath();
        String actualPath = route.getExpression();
        // 检查路径字符串
        Assert.assertEquals(expectedPath, actualPath);

        Map<String, String> expectedPathParameterMap = sample.getValues();
        Map<String, String> actualPathParameterMap = route.getPathParameterMap();
        // 检查路径参数数目
        Assert.assertEquals(expectedPathParameterMap.size(), actualPathParameterMap.size());

        // 检查路径参数的值
        for (Map.Entry<String, String> entry : expectedPathParameterMap.entrySet()) {
            Assert.assertEquals(entry.getValue(), actualPathParameterMap.get(entry.getKey()));
        }
    }

}
