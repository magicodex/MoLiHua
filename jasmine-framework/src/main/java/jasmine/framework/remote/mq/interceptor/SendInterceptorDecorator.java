package jasmine.framework.remote.mq.interceptor;

/**
 * @author mh.z
 */
public interface SendInterceptorDecorator {

    /**
     * 装饰拦截器
     *
     * @param interceptor
     * @return
     */
    SendInterceptor decorate(SendInterceptor interceptor);
}
