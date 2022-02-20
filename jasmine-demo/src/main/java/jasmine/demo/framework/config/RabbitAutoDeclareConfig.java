package jasmine.demo.framework.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public DirectExchange journalExchange() {
        return new DirectExchange(JOURNAL_EXCHANGE_NAME);
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
    public Binding journalNoticeQueueBinding(DirectExchange journalExchange, Queue journalNoticeQueue) {
        Binding binding = BindingBuilder
                .bind(journalNoticeQueue)
                .to(journalExchange)
                .with("notice");

        return binding;
    }

    @Bean
    public Binding journalSyncQueueBinding(DirectExchange journalExchange, Queue journalSyncQueue) {
        Binding binding = BindingBuilder
                .bind(journalSyncQueue)
                .to(journalExchange)
                .with("sync");

        return binding;
    }

}
