package jasmine.cloud.stream.testdependency;

import jasmine.core.util.NewUtil;
import jasmine.framework.remote.mq.MessageReceiver;

import java.util.List;

/**
 * @author mh.z
 */
public class MockMessageReceiver<T> implements MessageReceiver<T> {
    private Class<T> type;
    private List<T> receivedContents;

    public MockMessageReceiver(Class<T> type) {
        this.type = type;
        this.receivedContents = NewUtil.list();
    }

    public List<T> getReceivedContents() {
        return receivedContents;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public void receive(T content) {
        receivedContents.add(content);
    }

}
