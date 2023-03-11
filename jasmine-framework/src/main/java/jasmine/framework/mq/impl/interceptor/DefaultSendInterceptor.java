package jasmine.framework.mq.impl.interceptor;

import jasmine.framework.mq.interceptor.SendInterceptor;
import jasmine.framework.mq.interceptor.SendInvocation;
import jasmine.framework.mq.interceptor.SendInvocationInfo;

/**
 * @author mh.z
 */
public class DefaultSendInterceptor implements SendInterceptor {

    @Override
    public void onSend(SendInvocation invocation, String category, String key, Object message) {
        invocation.invoke(category, key, message);
    }

    @Override
    public void afterConvert(SendInvocationInfo callInfo) {
        //
    }

}
