package jasmine.framework.mq;

import jasmine.framework.mq.interceptor.SendInterceptorDecorator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <p>
 * 发送消息接口。
 * </p>
 *
 * @author mh.z
 */
public interface SendMessageService {

    /**
     * 发送消息
     *
     * @param category
     * @param key
     * @param content
     */
    void send(@Nonnull String category, @Nullable String key, Object content);

    /**
     * 发送消息
     *
     * @param category
     * @param key
     * @param content
     * @param decorator
     */
    void send(@Nonnull String category, @Nullable String key, Object content,
              @Nullable SendInterceptorDecorator decorator);
}
