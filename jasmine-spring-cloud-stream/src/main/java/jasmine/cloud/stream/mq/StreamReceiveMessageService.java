package jasmine.cloud.stream.mq;

import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.exception.InvalidParameterException;
import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.ObjectUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.framework.mq.MessageReceiver;
import jasmine.framework.mq.impl.AbstractReceiveMessageService;
import jasmine.framework.mq.impl.interceptor.DefaultReceiveInvocationInfo;
import jasmine.framework.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.mq.interceptor.ReceiveInvocationInfo;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * @author mh.z
 */
public class StreamReceiveMessageService extends AbstractReceiveMessageService<Message> {
    private RuntimeProvider runtimeProvider;

    private static final String HEADER_SUBJECT = "subject";
    private static final String PARAM_USER_ID = "userId";
    private static final String MESSAGE_KEY = "messageKey";
    /** 消息接收者 bean 的名称后缀 */
    private static final String RECEIVER_BEAN_SUFFIX = "MessageReceiver";
    /** 请求头分隔符 */
    private static final String HEADER_SEPARATOR_SYMBOL = ":";

    public StreamReceiveMessageService(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    protected ReceiveInvocationInfo doReceive(ReceiveInterceptor interceptor,
                                              String category, Message message) {
        CheckUtil.notNull(category, "category null");
        CheckUtil.notNull(message, "message null");

        // 初始安全上下文
        initCurrentContext(message);
        // 获取消息接收者
        MessageReceiver receiver = getReceiver(category, true);
        // 获取消息内容
        Object content = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        String messageId = headers.get(MESSAGE_KEY, String.class);

        ReceiveInvocationInfo invocationInfo = new DefaultReceiveInvocationInfo(messageId, content, message);
        // 转化消息后调用
        interceptor.afterConvert(invocationInfo);
        // 接收消息
        receiver.receive(content);

        return invocationInfo;
    }

    /**
     * 初始上下文
     *
     * @param message
     */
    protected void initCurrentContext(Message message) {
        CheckUtil.notNull(message, "message null");
        MessageHeaders headers = message.getHeaders();
        String subject = headers.get(HEADER_SUBJECT, String.class);

        // 初始安全上下文
        if (StringUtil.isNotEmpty(subject)) {
            if (subject.startsWith(PARAM_USER_ID + HEADER_SEPARATOR_SYMBOL)) {
                String userIdStr = subject.substring(7);
                Long userId = ObjectUtil.parseLong(userIdStr);

                CurrentSubject.setSubject(null, userId);
            }
        }
    }

    /**
     * 查找消息接收者并返回
     *
     * @param category
     * @param required
     * @return
     */
    protected MessageReceiver getReceiver(String category, boolean required) {
        CheckUtil.notNull(category, "category null");

        String receiverName = category + RECEIVER_BEAN_SUFFIX;
        MessageReceiver receiver = runtimeProvider.getByName(receiverName, false);

        if (receiver == null && required) {
            throw new InvalidParameterException(String.format("not found the %s(category=%s)",
                    MessageReceiver.class.getSimpleName(), category), null);
        }

        return receiver;
    }

}
