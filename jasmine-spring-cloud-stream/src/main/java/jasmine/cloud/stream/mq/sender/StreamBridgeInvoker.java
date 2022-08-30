package jasmine.cloud.stream.mq.sender;

/**
 * @author mh.z
 */
public interface StreamBridgeInvoker {

    /**
     * 发送数据
     *
     * @param bindingName
     * @param data
     * @return
     */
    boolean send(String bindingName, Object data);
}
