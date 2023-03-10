package jasmine.framework.remote.testdependency;

import jasmine.core.util.CheckUtil;
import jasmine.core.util.NewUtil;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocation;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;

import java.util.List;

/**
 * @author mh.z
 */
public class MockSendInterceptor implements SendInterceptor {
    private List<SendInvocationInfo> sendInvocationInfo;

    public MockSendInterceptor() {
        this.sendInvocationInfo = NewUtil.list();
    }

    public List<SendInvocationInfo> getSendInvocationInfo() {
        return sendInvocationInfo;
    }

    @Override
    public void onSend(SendInvocation invocation, String category, String key, Object message) {
        invocation.invoke(category, key, message);
    }

    @Override
    public void afterConvert(SendInvocationInfo invocationInfo) {
        CheckUtil.notNull(invocationInfo, "invocationInfo null");

        sendInvocationInfo.add(invocationInfo);
    }

}
