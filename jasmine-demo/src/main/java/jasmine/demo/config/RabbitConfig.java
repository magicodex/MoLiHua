package jasmine.demo.config;

import jasmine.framework.remote.mq.impl.routing.PublisherExchangeDirectRouting;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class RabbitConfig {
    @Value("${app.message-queue.rabbitmq.auto-declare:false}")
    private Boolean shouldDeclare;

    @Bean
    public DirectExchange sampleExchange() {
        DirectExchange exchange = new DirectExchange("demo.sample.exchange");
        exchange.setShouldDeclare(shouldDeclare);

        return exchange;
    }

    @Bean
    public Queue sampleQueue() {
        Queue queue = new Queue("demo.sample.queue");
        queue.setShouldDeclare(shouldDeclare);

        return queue;
    }

    @Bean
    public Binding sampleBinding(DirectExchange sampleExchange, Queue sampleQueue) {
        Binding binding = BindingBuilder
                .bind(sampleQueue)
                .to(sampleExchange)
                .with("default");
        binding.setShouldDeclare(shouldDeclare);

        return binding;
    }

    @Bean
    public PublisherExchangeDirectRouting sampleRouting(DirectExchange sampleExchange) {
        return new PublisherExchangeDirectRouting("sample", sampleExchange, "default");
    }

}
