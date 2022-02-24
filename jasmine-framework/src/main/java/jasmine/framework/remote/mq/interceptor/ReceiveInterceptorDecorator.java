package jasmine.framework.remote.mq.interceptor;

/**
 * @author mh.z
 */
public interface ReceiveInterceptorDecorator {

    /**
     * 装饰拦截器
     *
     * @return
     */
    ReceiveInterceptor decorate(ReceiveInterceptor interceptor);
}
