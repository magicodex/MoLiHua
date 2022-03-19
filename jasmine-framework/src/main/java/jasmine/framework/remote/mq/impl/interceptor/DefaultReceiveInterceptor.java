package jasmine.framework.remote.mq.impl.interceptor;

import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocation;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;

/**
 * @author mh.z
 */
public class DefaultReceiveInterceptor implements ReceiveInterceptor {

    @Override
    public void onReceive(ReceiveInvocation invocation, String category, Object content) {
        invocation.invoke(category, content);
    }

    @Override
    public void afterConvert(ReceiveInvocationInfo callInfo) {
        //
    }

}
