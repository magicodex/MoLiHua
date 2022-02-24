package jasmine.framework.remote.rabbit;

import jasmine.framework.remote.mq.routing.PublisherRouting;
import org.springframework.amqp.core.Exchange;

/**
 * @author mh.z
 */
public interface RabbitPublisherRouting extends PublisherRouting {

    /**
     * 返回交换器
     *
     * @return
     */
    Exchange getExchange();
}
