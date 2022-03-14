package jasmine.testconfigure.framework.middleware;

import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class MqTestConfiguration {

    @Bean
    public ReceiveMessageService receiveMessageService() {
        return Mockito.mock(ReceiveMessageService.class);
    }

    @Bean
    public SendMessageService sendMessageService() {
        return Mockito.mock(SendMessageService.class);
    }

}
