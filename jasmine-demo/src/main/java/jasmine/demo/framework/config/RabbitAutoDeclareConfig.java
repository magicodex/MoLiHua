package jasmine.demo.framework.config;

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
    private static final String JOURNAL_EXCHANGE_NAME = "demo.journal.exchange";
    private static final String JOURNAL_NOTICE_QUEUE_NAME = "demo.journal-notice.queue";
    private static final String JOURNAL_SYNC_QUEUE_NAME = "demo.journal-sync.queue";

    @Bean
    public HeadersExchange journalExchange() {
        return new HeadersExchange(JOURNAL_EXCHANGE_NAME);
    }

    @Bean
    public Queue journalNoticeQueue() {
        return new Queue(JOURNAL_NOTICE_QUEUE_NAME);
    }

    @Bean
    public Queue journalSyncQueue() {
        return new Queue(JOURNAL_SYNC_QUEUE_NAME);
    }

    @Bean
    public Binding journalNoticeQueueBinding(HeadersExchange journalExchange, Queue journalNoticeQueue) {
        Binding binding = BindingBuilder
                .bind(journalNoticeQueue)
                .to(journalExchange)
                .whereAny(Map.of("category", "notice"))
                .match();

        return binding;
    }

    @Bean
    public Binding journalSyncQueueBinding(HeadersExchange journalExchange, Queue journalSyncQueue) {
        Binding binding = BindingBuilder
                .bind(journalSyncQueue)
                .to(journalExchange)
                .whereAny(Map.of("category", "sync"))
                .match();

        return binding;
    }

}
