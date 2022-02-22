package jasmine.framework;

import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import org.springframework.core.task.TaskDecorator;

/**
 * <p>
 * 该接口里的方法是未自动注册的 bean。
 * </p>
 *
 * @author mh.z
 */
public interface JasmineFrameworkConfigTemplate {

    /**
     * 注册 ReceiveMessageService 方法
     *
     * @return
     */
    ReceiveMessageService receiveMessageService();

    /**
     * 注册 SendMessageService 方法
     *
     * @return
     */
    SendMessageService sendMessageService();

    /**
     * 注册 TaskDecorator 对象
     *
     * @return
     */
    TaskDecorator taskDecorator();
}
