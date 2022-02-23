package jasmine.framework.remote.rabbit;

import jasmine.framework.remote.mq.routing.PublisherRouting;
import org.springframework.amqp.core.Exchange;

public interface RabbitPublisherRouting extends PublisherRouting {

    Exchange getExchange();
}
