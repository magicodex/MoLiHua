package jasmine.framework.lock.testdependency;

import jasmine.framework.lock.annotation.DistributedLock;

import java.util.Map;

/**
 * @author mh.z
 */
public class TestObject1 {

    @DistributedLock(category = "test", key = "#key")
    public Object call(Object key, Object value) {
        return key + ":" + value;
    }

}
