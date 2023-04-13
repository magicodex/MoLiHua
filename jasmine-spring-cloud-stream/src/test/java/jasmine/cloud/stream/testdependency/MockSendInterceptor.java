package jasmine.cloud.stream.testdependency;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.NewUtil;
import jasmine.framework.mq.interceptor.SendInterceptor;
import jasmine.framework.mq.interceptor.SendInvocation;
import jasmine.framework.mq.interceptor.SendInvocationInfo;

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
