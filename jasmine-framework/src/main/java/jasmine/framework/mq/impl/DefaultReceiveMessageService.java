package jasmine.framework.mq.impl;

import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.common.exception.InvalidParameterException;
import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.ObjectUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.framework.common.util.SimpleConvertUtil;
import jasmine.framework.mq.MessageReceiver;
import jasmine.framework.mq.impl.interceptor.DefaultReceiveInvocationInfo;
import jasmine.framework.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.mq.interceptor.ReceiveInvocationInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * @author mh.z
 */
public class DefaultReceiveMessageService extends AbstractReceiveMessageService<Message> {
    private RuntimeProvider runtimeProvider;

    private static final String HEADER_SUBJECT = "subject";
    private static final String PARAM_USER_ID = "userId";
    /** 消息接收者 bean 的名称后缀 */
    private static final String RECEIVER_BEAN_SUFFIX = "MessageReceiver";
    /** 请求头分隔符 */
    private static final String HEADER_SEPARATOR_SYMBOL = ":";

    public DefaultReceiveMessageService(RuntimeProvider runtimeProvider) {
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
        Class<?> targetType = receiver.getType();
        Object content = getContent(message, targetType);
        MessageProperties messageProperties = message.getMessageProperties();
        String messageId = messageProperties.getMessageId();

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
        MessageProperties messageProperties = message.getMessageProperties();
        String subject = messageProperties.getHeader(HEADER_SUBJECT);

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
     * 返回消息内容
     *
     * @param message
     * @param targetType
     * @return
     */
    protected Object getContent(Message message, Class<?> targetType) {
        CheckUtil.notNull(message, "message null");
        byte[] messageBody = message.getBody();
        Object content = null;

        // 转换成指定的类型
        if (targetType != null) {
            content = SimpleConvertUtil.deserialize(messageBody, targetType);
        } else {
            content = messageBody;
        }

        return content;
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
