package jasmine.cloud.stream.mq;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.exception.InvalidParameterException;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.remote.mq.MessageReceiver;
import jasmine.framework.remote.mq.impl.AbstractReceiveMessageService;
import jasmine.framework.remote.mq.impl.interceptor.DefaultReceiveInvocationInfo;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;
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
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");

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
        QCheckUtil.notNull(message, "message null");
        MessageHeaders headers = message.getHeaders();
        String subject = headers.get(HEADER_SUBJECT, String.class);

        // 初始安全上下文
        if (QStringUtil.isNotEmpty(subject)) {
            if (subject.startsWith(PARAM_USER_ID + HEADER_SEPARATOR_SYMBOL)) {
                String userIdStr = subject.substring(7);
                Long userId = QObjectUtil.parseLong(userIdStr);

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
        QCheckUtil.notNull(category, "category null");

        String receiverName = category + RECEIVER_BEAN_SUFFIX;
        MessageReceiver receiver = runtimeProvider.getByName(receiverName, false);

        if (receiver == null && required) {
            throw new InvalidParameterException(String.format("not found the %s(category=%s)",
                    MessageReceiver.class.getSimpleName(), category), null);
        }

        return receiver;
    }

}
