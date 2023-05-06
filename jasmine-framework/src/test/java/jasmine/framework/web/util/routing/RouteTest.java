package jasmine.framework.web.util.routing;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class RouteTest {

    @Test
    public void testGetPathParameterMap() {
        // 有一个路径参数
        {
            PathInfo info = new PathInfo(null, null, new String[]{null, null, "3", null, null});
            Route route = new Route(info, new String[]{"a", "b", "c", "d", "e"});
            Map<String, String> pathParameterMap = route.getPathParameterMap();

            Assert.assertEquals(1, pathParameterMap.size());
            Assert.assertEquals("c", pathParameterMap.get("3"));
        }

        // 有两个路径参数
        {
            PathInfo info = new PathInfo(null, null, new String[]{null, null, "3", null, "5"});
            Route route = new Route(info, new String[]{"a", "b", "c", "d", "e"});
            Map<String, String> pathParameterMap = route.getPathParameterMap();

            Assert.assertEquals(2, pathParameterMap.size());
            Assert.assertEquals("c", pathParameterMap.get("3"));
            Assert.assertEquals("e", pathParameterMap.get("5"));
        }

        // 没有路径参数
        {
            PathInfo info = new PathInfo(null, null, new String[]{null, null, null, null, null});
            Route route = new Route(info, new String[]{"a", "b", "c", "d", "e"});
            Map<String, String> pathParameterMap = route.getPathParameterMap();

            Assert.assertEquals(0, pathParameterMap.size());
        }
    }

}
