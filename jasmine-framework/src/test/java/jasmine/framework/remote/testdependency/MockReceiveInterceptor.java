package jasmine.framework.remote.testdependency;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocation;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;

import java.util.Collection;

/**
 * @author mh.z
 */
public class MockReceiveInterceptor implements ReceiveInterceptor {
    private Multimap<String, Object> receivedData;
    private ReceiveInvocation lastInvocation;
    private ReceiveInvocationInfo lastInvocationInfo;

    public MockReceiveInterceptor() {
        this.receivedData = ArrayListMultimap.create();
    }

    public Collection<Object> getReceivedContents(String category) {
        return receivedData.get(category);
    }

    public ReceiveInvocation getLastInvocation() {
        return lastInvocation;
    }

    public ReceiveInvocationInfo getLastInvocationInfo() {
        return lastInvocationInfo;
    }

    @Override
    public void onReceive(ReceiveInvocation invocation, String category, Object content) {
        this.lastInvocation = invocation;
        receivedData.put(category, content);
    }

    @Override
    public void afterConvert(ReceiveInvocationInfo invocationInfo) {
        this.lastInvocationInfo = invocationInfo;
    }

}
