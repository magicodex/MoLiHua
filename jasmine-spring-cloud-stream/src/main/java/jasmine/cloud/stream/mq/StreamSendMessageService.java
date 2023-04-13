package jasmine.cloud.stream.mq;

import jasmine.cloud.stream.mq.sender.StreamBridgeInvoker;
import jasmine.framework.context.CurrentSubject;
import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.framework.database.mybatisplus.util.UniqueKeyUtil;
import jasmine.framework.mq.impl.AbstractSendMessageService;
import jasmine.framework.mq.impl.interceptor.DefaultSendInvocationInfo;
import jasmine.framework.mq.interceptor.SendInterceptor;
import jasmine.framework.mq.interceptor.SendInvocationInfo;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * @author mh.z
 */
public class StreamSendMessageService extends AbstractSendMessageService {
    private StreamBridgeInvoker streamBridgeInvoker;

    private static final String HEADER_SUBJECT = "subject";
    private static final String PARAM_USER_ID = "userId";
    private static final String MESSAGE_KEY = "messageKey";
    private static final String OUTPUT_PREFIX = "-out-0";
    private static final String HEADER_SEPARATOR_SYMBOL = ":";

    public StreamSendMessageService(StreamBridgeInvoker streamBridgeInvoker) {
        this.streamBridgeInvoker = streamBridgeInvoker;
    }

    @Override
    protected SendInvocationInfo doSend(SendInterceptor interceptor, String category,
                                        String key, Object content) {
        CheckUtil.notNull(interceptor, "interceptor null");
        CheckUtil.notNull(category, "category null");

        // 创建要发送的消息对象
        Message message = createMessage(key, content);

        DefaultSendInvocationInfo invocationInfo = new DefaultSendInvocationInfo(key, content, message);
        // 转化消息后调用
        interceptor.afterConvert(invocationInfo);

        // 发送消息
        String bindingName = category + OUTPUT_PREFIX;
        streamBridgeInvoker.send(bindingName, message);

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
            key = StringUtil.toString(UniqueKeyUtil.nextLong());
        }

        MessageBuilder builder = MessageBuilder.withPayload(content);
        // 设置安全信息
        String userIdStr = StringUtil.orEmpty(CurrentSubject.getUserId());
        builder.setHeader(HEADER_SUBJECT, (PARAM_USER_ID + HEADER_SEPARATOR_SYMBOL + userIdStr));
        // 设置消息ID
        builder.setHeader(MESSAGE_KEY, key);

        return builder.build();
    }

}
