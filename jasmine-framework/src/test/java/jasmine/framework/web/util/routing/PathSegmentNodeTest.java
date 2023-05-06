package jasmine.framework.web.util.routing;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author mh.z
 */
public class PathSegmentNodeTest {

    @Test
    public void testAdd() {
        PathSegmentNode root = new PathSegmentNode();
        PathInfo info = createPathInfo();
        root.add(new String[]{"", "1", null, "3"}, 1, info);
        root.add(new String[]{"", "1", "2", "3"}, 1, info);

        // /1
        {
            PathSegmentNode node = getPathNode(root, "1");
            Assert.assertNotNull(node);
            Assert.assertEquals(2, node.getNextNodeMap().size());
            Assert.assertNotNull(node.getNextNodeMap().get(null));
            Assert.assertNotNull(node.getNextNodeMap().get("2"));
        }

        // /1/null
        {
            PathSegmentNode node = getPathNode(root, "1", null);
            Assert.assertNotNull(node);
            Assert.assertEquals(1, node.getNextNodeMap().size());
            Assert.assertNotNull(node.getNextNodeMap().get("3"));
        }

        // /1/2
        {
            PathSegmentNode node = getPathNode(root, "1", "2");
            Assert.assertNotNull(node);
            Assert.assertEquals(1, node.getNextNodeMap().size());
            Assert.assertNotNull(node.getNextNodeMap().get("3"));
        }

        // /1/null/3
        {
            PathSegmentNode node = getPathNode(root, "1", null, "3");
            Assert.assertNotNull(node);
            Assert.assertEquals(0, node.getNextNodeMap().size());
        }

        // /1/2/3
        {
            PathSegmentNode node = getPathNode(root, "1", "2", "3");
            Assert.assertNotNull(node);
            Assert.assertEquals(0, node.getNextNodeMap().size());
        }
    }

    @Test
    public void testGet() {
        PathSegmentNode root = new PathSegmentNode();
        PathSegmentNode node1 = new PathSegmentNode(new HashMap<>(),
                new PathInfo(null, "/1", null));
        PathSegmentNode node2 = new PathSegmentNode(new HashMap<>(),
                new PathInfo(null, "/1/null", null));
        PathSegmentNode node3 = new PathSegmentNode(new HashMap<>(),
                new PathInfo(null, "/1/2", null));
        PathSegmentNode node4 = new PathSegmentNode(new HashMap<>(),
                new PathInfo(null, "/1/null/3", null));
        PathSegmentNode node5 = new PathSegmentNode(new HashMap<>(),
                new PathInfo(null, "/1/2/3", null));

        root.getNextNodeMap().put("1", node1);
        node1.getNextNodeMap().put(null, node2);
        node1.getNextNodeMap().put("2", node3);
        node2.getNextNodeMap().put("3", node4);
        node3.getNextNodeMap().put("3", node5);

        // /1
        {
            PathInfo info = root.get(new String[]{"1"}, 0);
            Assert.assertNotNull(info);
            Assert.assertEquals("/1", info.getBindingValue());
        }

        // /1/null
        {
            PathInfo info = root.get(new String[]{"1", "b"}, 0);
            Assert.assertNotNull(info);
            Assert.assertEquals("/1/null", info.getBindingValue());
        }

        // /1/2
        {
            PathInfo info = root.get(new String[]{"1", "2"}, 0);
            Assert.assertNotNull(info);
            Assert.assertEquals("/1/2", info.getBindingValue());
        }

        // /1/null/3
        {
            PathInfo info = root.get(new String[]{"1", "b", "3"}, 0);
            Assert.assertNotNull(info);
            Assert.assertEquals("/1/null/3", info.getBindingValue());
        }

        // /1/2/3
        {
            PathInfo info = root.get(new String[]{"1", "2", "3"}, 0);
            Assert.assertNotNull(info);
            Assert.assertEquals("/1/2/3", info.getBindingValue());
        }
    }

    /**
     * 返回路径节点
     *
     * @param pathNode 路径节点
     * @param pathSegments 路径片段
     * @return 路径节点
     */
    private PathSegmentNode getPathNode(PathSegmentNode pathNode, String... pathSegments) {
        PathSegmentNode currentNode = pathNode;

        for (int i = 0; currentNode != null && i < pathSegments.length; i++) {
            currentNode = currentNode.getNextNodeMap().get(pathSegments[i]);
        }

        return currentNode;
    }

    /**
     * 创建路径信息
     *
     * @return 路径信息
     */
    private PathInfo createPathInfo() {
        PathInfo pathInfo = new PathInfo(null, null, null);
        return pathInfo;
    }

}
