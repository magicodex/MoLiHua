package jasmine.framework;

import jasmine.framework.cache.CacheSyncStrategy;
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
    default ReceiveMessageService receiveMessageService() {
        return null;
    }

    /**
     * 注册 SendMessageService 方法
     *
     * @return
     */
    default SendMessageService sendMessageService() {
        return null;
    }

    /**
     * 注册 CacheSyncStrategy 方法
     *
     * @return
     */
    default CacheSyncStrategy cacheSyncStrategy() {
        return null;
    }

    /**
     * 注册 TaskDecorator 对象
     *
     * @return
     */
    default TaskDecorator taskDecorator() {
        return null;
    }

}
