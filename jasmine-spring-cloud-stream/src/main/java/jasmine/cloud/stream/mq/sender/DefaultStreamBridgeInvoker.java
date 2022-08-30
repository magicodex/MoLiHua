package jasmine.cloud.stream.mq.sender;

import org.springframework.cloud.stream.function.StreamBridge;

/**
 * @author mh.z
 */
public class DefaultStreamBridgeInvoker implements StreamBridgeInvoker {
    private StreamBridge streamBridge;

    public DefaultStreamBridgeInvoker(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public boolean send(String bindingName, Object data) {
        return streamBridge.send(bindingName, data);
    }

}
