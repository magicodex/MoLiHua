package jasmine.framework.web.util.routing;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mh.z
 */
public class Route {
    /** 路径信息 */
    private PathInfo pathInfo;
    /** 字符"/"分隔的路径，第一个路径片段是索引1 */
    private String[] pathSegments;

    public Route(PathInfo pathInfo, String[] pathSegments) {
        this.pathInfo = pathInfo;
        this.pathSegments = pathSegments;
    }

    public String getExpression() {
        String path = pathInfo.getExpression();
        return path;
    }

    public Object getBindingValue() {
        Object item = pathInfo.getBindingValue();
        return item;
    }

    /**
     * 返回路径参数
     *
     * @return 路径参数
     */
    public Map<String, String> getPathParameterMap() {
        Map<String, String> pathParameterMap = new HashMap<String, String>();
        String[] pathParameterNames = pathInfo.getPathParameterNames();
        String pathParameterName = null;

        // 第一个路径片段是索引1
        for (int i = 1; i < pathParameterNames.length && i < pathSegments.length; i++) {
            pathParameterName = pathParameterNames[i];

            // 元素不是null则是路径参数名
            if (pathParameterName != null) {
                pathParameterMap.put(pathParameterName, pathSegments[i]);
            }
        }

        return pathParameterMap;
    }

}
