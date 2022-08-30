package jasmine.cloud.stream.testdependency;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import jasmine.cloud.stream.mq.sender.StreamBridgeInvoker;
import jasmine.core.util.QCheckUtil;

import java.util.Collection;

/**
 * @author mh.z
 */
public class MockStreamBridgeInvoker implements StreamBridgeInvoker {
    private Multimap<String, Object> sentData;

    public MockStreamBridgeInvoker() {
        this.sentData = ArrayListMultimap.create();
    }

    public Collection<Object> getData(String bindingName) {
        return sentData.get(bindingName);
    }

    @Override
    public boolean send(String bindingName, Object data) {
        QCheckUtil.notNull(bindingName, "bindingName null");

        sentData.put(bindingName, data);
        return true;
    }

}
