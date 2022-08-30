package jasmine.cloud.stream.autoconfigure;

import jasmine.cloud.stream.mq.StreamReceiveMessageService;
import jasmine.cloud.stream.mq.StreamSendMessageService;
import jasmine.cloud.stream.mq.sender.DefaultStreamBridgeInvoker;
import jasmine.core.context.RuntimeProvider;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@ConditionalOnProperty(value = "jasmine.message-queue.type",
        havingValue = "stream", matchIfMissing = false)
@Configuration
public class JasmineCloudStreamAutoConfigure {

    @Bean
    public ReceiveMessageService receiveMessageService(RuntimeProvider runtimeProvider) {
        return new StreamReceiveMessageService(runtimeProvider);
    }

    @Bean
    public SendMessageService sendMessageService(StreamBridge streamBridge) {
        DefaultStreamBridgeInvoker invoker = new DefaultStreamBridgeInvoker(streamBridge);

        return new StreamSendMessageService(invoker);
    }

}
