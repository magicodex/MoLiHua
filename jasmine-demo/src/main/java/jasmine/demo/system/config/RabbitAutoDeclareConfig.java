package jasmine.demo.system.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author mh.z
 */
@Configuration
@ConditionalOnProperty(value = "app.message-queue.rabbitmq.auto-declare",
        havingValue = "true", matchIfMissing = false)
public class RabbitAutoDeclareConfig {
    private static final String EXAMPLE_EXCHANGE_NAME1 = "demo.example.exchange1";
    private static final String EXAMPLE_QUEUE_NAME1 = "demo.example.queue1";
    private static final String EXAMPLE_QUEUE_NAME2 = "demo.example.queue2";

    @Bean
    public HeadersExchange exampleExchange1() {
        return new HeadersExchange(EXAMPLE_EXCHANGE_NAME1);
    }

    //
    // example队列1
    //

    @Bean
    public Queue exampleQueue1() {
        return new Queue(EXAMPLE_QUEUE_NAME1);
    }

    @Bean
    public Binding exampleBinding1(HeadersExchange exampleExchange1, Queue exampleQueue1) {
        Binding binding = BindingBuilder
                .bind(exampleQueue1)
                .to(exampleExchange1)
                .whereAny(Map.of("category", "example1"))
                .match();

        return binding;
    }

    //
    // example队列2
    //

    @Bean
    public Queue exampleQueue2() {
        return new Queue(EXAMPLE_QUEUE_NAME2);
    }

    @Bean
    public Binding exampleBinding2(HeadersExchange exampleExchange1, Queue exampleQueue2) {
        Binding binding = BindingBuilder
                .bind(exampleQueue2)
                .to(exampleExchange1)
                .whereAny(Map.of("category", "example2"))
                .match();

        return binding;
    }

}
