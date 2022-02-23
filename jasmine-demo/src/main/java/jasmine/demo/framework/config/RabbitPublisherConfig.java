package jasmine.demo.framework.config;

import jasmine.framework.remote.rabbit.RabbitPublisherDirectRouting;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitPublisherConfig {

    @Bean
    public RabbitPublisherDirectRouting journalNoticePublisherRouting(DirectExchange journalExchange) {
        return new RabbitPublisherDirectRouting("journalNotice", journalExchange, "notice");
    }

}
