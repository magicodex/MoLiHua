package jasmine.framework.remote.mq.routing;

import jasmine.framework.remote.mq.routing.PublisherRouting;
import org.springframework.amqp.core.Exchange;

/**
 * @author mh.z
 */
public interface PublisherExchangeRouting extends PublisherRouting {

    /**
     * 返回交换器
     *
     * @return
     */
    Exchange getExchange();
}