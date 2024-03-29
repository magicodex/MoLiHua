package jasmine.framework.mq.impl.routing;

import org.springframework.amqp.core.Exchange;

/**
 * @author mh.z
 */
public class PublisherExchangeDirectRouting extends AbstractPublisherExchangeRouting {
    /** 路由key */
    private String routingKey;

    public PublisherExchangeDirectRouting(String category, Exchange exchange, String routingKey) {
        super(category, exchange);
        this.routingKey = routingKey;
    }

    public String getRoutingKey() {
        return routingKey;
    }

}
