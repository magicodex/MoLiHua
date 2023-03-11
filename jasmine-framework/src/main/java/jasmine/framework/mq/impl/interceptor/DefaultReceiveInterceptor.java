package jasmine.framework.mq.impl.interceptor;

import jasmine.framework.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.mq.interceptor.ReceiveInvocation;
import jasmine.framework.mq.interceptor.ReceiveInvocationInfo;

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
