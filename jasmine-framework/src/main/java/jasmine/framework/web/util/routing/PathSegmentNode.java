package jasmine.framework.web.util.routing;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.NewUtil;

import java.util.Map;

/**
 * @author mh.z
 */
public class PathSegmentNode {
    /** 下个路径节点 */
    private Map<String, PathSegmentNode> nextNodeMap;
    /** 路径信息 */
    private PathInfo pathInfo;

    public PathSegmentNode() {
        this.nextNodeMap = NewUtil.map();
        this.pathInfo = null;
    }

    public PathSegmentNode(Map<String, PathSegmentNode> nextNodeMap, PathInfo pathInfo) {
        this.nextNodeMap = nextNodeMap;
        this.pathInfo = pathInfo;
    }

    public Map<String, PathSegmentNode> getNextNodeMap() {
        return nextNodeMap;
    }

    public PathInfo getPathInfo() {
        return pathInfo;
    }

    /**
     * 添加路径
     *
     * @param pathSegments 路径片段
     * @param index 索引
     * @param pathInfo 路径信息
     */
    public void add(String[] pathSegments, int index, PathInfo pathInfo) {
        CheckUtil.notNull(pathSegments, "pathSegments null");

        if (index >= pathSegments.length) {
            if (this.pathInfo != null) {
                throw new RuntimeException("path exists");
            }

            this.pathInfo = pathInfo;
            return;
        }

        String segment = pathSegments[index];
        PathSegmentNode node = nextNodeMap.get(segment);

        // 添加节点
        if (node == null) {
            node = new PathSegmentNode();
            nextNodeMap.put(segment, node);
        }

        // 添加路径
        node.add(pathSegments, index + 1, pathInfo);
    }

    /**
     * 返回路径信息
     *
     * @param pathSegments 路径片段
     * @param index 索引
     * @return 路径信息
     */
    public PathInfo get(String[] pathSegments, int index) {
        CheckUtil.notNull(pathSegments, "pathSegments null");

        if (index >= pathSegments.length) {
            return pathInfo;
        }

        String segment = pathSegments[index];
        PathSegmentNode node = nextNodeMap.get(segment);
        PathInfo info = null;

        if (node != null) {
            info = node.get(pathSegments, index + 1);
        }

        if (info == null) {
            // null代表路径参数
            node = nextNodeMap.get(null);

            if (node != null) {
                info = node.get(pathSegments, index + 1);
            }
        }

        return info;
    }

}
