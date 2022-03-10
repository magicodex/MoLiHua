package jasmine.framework.remote.mq.impl.interceptor;

import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocation;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;

/**
 * @author mh.z
 */
public class DefaultSendInterceptor implements SendInterceptor {

    @Override
    public void onSend(SendInvocation invocation, String key, String category, Object message) {
        invocation.invoke(category, key, message);
    }

    @Override
    public void afterSerialize(SendInvocationInfo callInfo) {
        //
    }

}
