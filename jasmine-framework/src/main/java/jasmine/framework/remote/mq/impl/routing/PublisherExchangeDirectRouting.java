package jasmine.framework.remote.mq.impl.routing;

import org.springframework.amqp.core.Exchange;

/**
 * @author mh.z
 */
public class PublisherExchangeDirectRouting extends AbstractPublisherExchangeRouting {
    private String routingKey;

    public PublisherExchangeDirectRouting(String category, Exchange exchange, String routingKey) {
        super(category, exchange);
        this.routingKey = routingKey;
    }

    public String getRoutingKey() {
        return routingKey;
    }

}
