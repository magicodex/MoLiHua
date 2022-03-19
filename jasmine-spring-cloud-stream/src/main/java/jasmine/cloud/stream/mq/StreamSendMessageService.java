package jasmine.cloud.stream.mq;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.common.util.UniqueKeyUtil;
import jasmine.framework.remote.mq.impl.AbstractSendMessageService;
import jasmine.framework.remote.mq.impl.interceptor.DefaultSendInvocationInfo;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * @author mh.z
 */
public class StreamSendMessageService extends AbstractSendMessageService {
    private StreamBridge streamBridge;

    private static final String HEADER_SUBJECT = "subject";
    private static final String PARAM_USER_ID = "userId";
    private static final String MESSAGE_KEY = "messageKey";

    public StreamSendMessageService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    protected SendInvocationInfo doSend(SendInterceptor interceptor, String category,
                                        String key, Object content) {
        QCheckUtil.notNull(interceptor, "interceptor null");
        QCheckUtil.notNull(category, "category null");

        // 创建要发送的消息对象
        Message message = createMessage(key, content);

        DefaultSendInvocationInfo invocationInfo = new DefaultSendInvocationInfo(key, content, message);
        // 转化消息后调用
        interceptor.afterConvert(invocationInfo);

        // 发送消息
        String bindingName = category;
        streamBridge.send(bindingName, message);

        return invocationInfo;
    }

    /**
     * 创建消息对象
     *
     * @param key
     * @param content
     * @return
     */
    protected Message createMessage(String key, Object content) {
        // 保证消息key不能为空
        if (key == null) {
            key = QStringUtil.toString(UniqueKeyUtil.nextLong());
        }

        MessageBuilder builder = MessageBuilder.withPayload(content);
        // 设置安全信息
        String userIdStr = QStringUtil.orEmpty(CurrentSubject.getUserId());
        builder.setHeader(HEADER_SUBJECT, (PARAM_USER_ID + ":" + userIdStr));
        // 设置消息ID
        builder.setHeader(MESSAGE_KEY, key);

        return builder.build();
    }

}
