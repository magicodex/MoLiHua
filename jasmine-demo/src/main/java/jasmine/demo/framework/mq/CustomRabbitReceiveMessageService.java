package jasmine.demo.framework.mq;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;
import jasmine.demo.authentication.persistence.entity.UserEO;
import jasmine.demo.authentication.persistence.mapper.UserMapper;
import jasmine.demo.framework.security.UserSubjectProvider;
import jasmine.framework.remote.rabbit.RabbitReceiveMessageService;
import jasmine.security.subject.UserSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * @author mh.z
 */
public class CustomRabbitReceiveMessageService extends RabbitReceiveMessageService {
    private static final Logger logger = LoggerFactory.getLogger(CustomRabbitReceiveMessageService.class);
    private UserSubjectProvider userSubjectProvider;
    private UserMapper userMapper;

    public CustomRabbitReceiveMessageService(RuntimeProvider runtimeProvider) {
        super(runtimeProvider);
        this.userSubjectProvider = runtimeProvider.getByType(UserSubjectProvider.class);
        this.userMapper = runtimeProvider.getByType(UserMapper.class);
    }

    @Override
    protected void doReceive(String category, Message message) {
        try {
            MessageProperties messageProperties = message.getMessageProperties();
            String subject = messageProperties.getHeader("subject");

            if (QStringUtil.isNotEmpty(subject)) {
                if (subject.startsWith("userId:")) {
                    String userIdStr = subject.substring(7);
                    Long userId = QObjectUtil.parseLong(userIdStr);

                    UserEO userEO = userMapper.selectById(userId);
                    if (userEO != null) {
                        UserSubject userSubject = new UserSubject(userEO.getTenantId(), userId);
                        userSubjectProvider.setCurrentSubject(userSubject);
                    }
                }
            }

            super.doReceive(category, message);
        } catch (Exception e) {
            logger.error("receive failed", e);
        }
    }

}
