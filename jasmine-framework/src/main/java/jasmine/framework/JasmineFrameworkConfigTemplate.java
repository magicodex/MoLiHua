package jasmine.framework;

import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import org.springframework.context.annotation.Bean;

/**
 * @author mh.z
 */
public interface JasmineFrameworkConfigTemplate {

    ReceiveMessageService receiveMessageService();

    SendMessageService sendMessageService();
}
