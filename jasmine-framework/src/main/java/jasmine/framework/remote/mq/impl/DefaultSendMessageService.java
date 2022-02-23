package jasmine.framework.remote.mq.impl;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.MessageSender;

/**
 * @author mh.z
 */
public class DefaultSendMessageService extends AbstractSendMessageService {

    public DefaultSendMessageService(RuntimeProvider runtimeProvider) {
        super(runtimeProvider);
    }

    /**
     * 发送消息
     *
     * @param category
     * @param content
     */
    @Override
    protected void doSend(String category, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        // 获取消息发送者
        MessageSender sender = getSender(category, true);
        // 发送消息
        sender.send(null, content);
    }

}
