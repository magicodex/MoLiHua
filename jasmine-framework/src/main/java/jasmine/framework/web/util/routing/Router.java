package jasmine.framework.web.util.routing;

import jasmine.framework.common.util.CheckUtil;

/**
 * @author mh.z
 */
public class Router {
    /** 路径根节点 */
    private PathSegmentNode root;

    public Router() {
        root = new PathSegmentNode();
    }

    /**
     * 添加路径
     *
     * @param path 路径
     * @param value 路径对应的值
     */
    public void addRoute(String path, Object value) {
        CheckUtil.notNull(path, "path null");
        CheckUtil.notNull(value, "value null");

        // 获取路径片段
        String[] pathSegments = getPathSegments(path);
        String[] pathParameterNames = new String[pathSegments.length];
        String pathSegment = null;
        String pathParameterName = null;

        // 获取路径参数名
        for (int i = 1; i < pathSegments.length; i++) {
            pathSegment = pathSegments[i];
            pathParameterName = getPathParameterName(pathSegment);

            if (pathParameterName != null) {
                pathSegments[i] = null;
                pathParameterNames[i] = pathParameterName;
            }
        }

        PathInfo pathInfo = new PathInfo(path, value, pathParameterNames);
        // 添加路径
        root.add(pathSegments, 1, pathInfo);
    }

    /**
     * 返回匹配的结果
     *
     * @param path 路径
     * @return 匹配的结果
     */
    public Route getRoute(String path) {
        CheckUtil.notNull(path, "path null");

        String[] pathSegments = getPathSegments(path);
        PathInfo pathInfo = root.get(pathSegments, 1);

        if (pathInfo == null) {
            return null;
        }

        Route route = new Route(pathInfo, pathSegments);
        return route;
    }

    /**
     * 返回路径片段
     *
     * @param path 路径
     * @return 路径片段，第一个路径片段的索引是1
     */
    protected String[] getPathSegments(String path) {
        CheckUtil.notNull(path, "path null");

        String[] pathSegments = path.split("/", -1);
        return pathSegments;
    }

    /**
     * 返回路径参数名
     *
     * @param pathSegment 路径片段
     * @return 路径参数名，不是路径参数则返回null
     */
    protected String getPathParameterName(String pathSegment) {
        CheckUtil.notNull(pathSegment, "pathSegment null");

        String pathParameterName = null;

        // 路径参数以":"开头
        if (pathSegment.startsWith(":")) {
            pathParameterName = pathSegment.substring(1);
        }

        return pathParameterName;
    }

}
