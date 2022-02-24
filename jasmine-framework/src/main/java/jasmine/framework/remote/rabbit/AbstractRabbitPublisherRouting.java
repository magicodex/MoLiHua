package jasmine.framework.remote.rabbit;

import org.springframework.amqp.core.Exchange;

/**
 * @author mh.z
 */
public abstract class AbstractRabbitPublisherRouting implements RabbitPublisherRouting {
    private String category;
    private Exchange exchange;

    public AbstractRabbitPublisherRouting(String category, Exchange exchange) {
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
