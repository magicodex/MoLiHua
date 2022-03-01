package jasmine.framework.remote.mq.impl.routing;

import jasmine.framework.remote.mq.routing.PublisherExchangeRouting;
import org.springframework.amqp.core.Exchange;

/**
 * @author mh.z
 */
public abstract class AbstractPublisherExchangeRouting implements PublisherExchangeRouting {
    private String category;
    private Exchange exchange;

    public AbstractPublisherExchangeRouting(String category, Exchange exchange) {
        this.category = category;
        this.exchange = exchange;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public Exchange getExchange() {
        return exchange;
    }

}
