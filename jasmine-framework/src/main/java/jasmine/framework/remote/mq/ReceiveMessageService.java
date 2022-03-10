package jasmine.framework.remote.mq;

import jasmine.framework.remote.mq.interceptor.ReceiveInterceptorDecorator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <p>
 * 接收消息接口。
 * </p>
 *
 * @author mh.z
 */
public interface ReceiveMessageService {

    /**
     * 接收消息
     *
     * @param category
     * @param message
     */
    void receive(@Nonnull String category, Object message);

    /**
     * 接收消息
     *
     * @param category
     * @param message
     * @param decorator
     */
    void receive(@Nonnull String category, Object message,
                 @Nullable ReceiveInterceptorDecorator decorator);
}
