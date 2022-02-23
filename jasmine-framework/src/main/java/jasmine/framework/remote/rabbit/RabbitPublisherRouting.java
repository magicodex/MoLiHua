package jasmine.framework.remote.rabbit;

import jasmine.framework.remote.mq.MessagePublisherRouting;
import org.springframework.amqp.core.Exchange;

public interface RabbitPublisherRouting extends MessagePublisherRouting {

    Exchange getExchange();
}
