package jasmine.framework.remote.rabbit;

import org.springframework.amqp.core.Exchange;

/**
 * @author mh.z
 */
public class RabbitPublisherDirectRouting extends AbstractRabbitPublisherRouting {
    private String routingKey;

    public RabbitPublisherDirectRouting(String category, Exchange exchange, String routingKey) {
        super(category, exchange);
        this.routingKey = routingKey;
    }

    public String getRoutingKey() {
        return routingKey;
    }

}
