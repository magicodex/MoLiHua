package jasmine.demo.framework.config;

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
public class RabbitAutoDeclareConfig {
    private static final String JOURNAL_EXCHANGE_NAME = "demo.journal.exchange";
    private static final String JOURNAL_NOTICE_QUEUE_NAME = "demo.journal-notice.queue";
    private static final String JOURNAL_SYNC_QUEUE_NAME = "demo.journal-sync.queue";

    @Value("${app.message-queue.rabbitmq.auto-declare:false}")
    private boolean shouldDeclare;

    @Bean
    public DirectExchange journalExchange() {
        DirectExchange exchange = new DirectExchange(JOURNAL_EXCHANGE_NAME);
        exchange.setShouldDeclare(shouldDeclare);

        return exchange;
    }

    @Bean
    public Queue journalNoticeQueue() {
        Queue queue = new Queue(JOURNAL_NOTICE_QUEUE_NAME);
        queue.setShouldDeclare(shouldDeclare);

        return queue;
    }

    @Bean
    public Queue journalSyncQueue() {
        Queue queue = new Queue(JOURNAL_SYNC_QUEUE_NAME);
        queue.setShouldDeclare(shouldDeclare);

        return queue;
    }

    @Bean
    public Binding journalNoticeQueueBinding(DirectExchange journalExchange, Queue journalNoticeQueue) {
        Binding binding = BindingBuilder
                .bind(journalNoticeQueue)
                .to(journalExchange)
                .with("notice");
        binding.setShouldDeclare(shouldDeclare);

        return binding;
    }

    @Bean
    public Binding journalSyncQueueBinding(DirectExchange journalExchange, Queue journalSyncQueue) {
        Binding binding = BindingBuilder
                .bind(journalSyncQueue)
                .to(journalExchange)
                .with("sync");
        binding.setShouldDeclare(shouldDeclare);

        return binding;
    }

}
