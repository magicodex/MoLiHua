package jasmine.autoconfigure.framework.middleware;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.message-queue")
public class MessageQueueProperties {
    /** 消费者相关配置 */
    private Consumer consumer = new Consumer();
    /** 发布者相关配置 */
    private Publisher publisher = new Publisher();

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 消费者相关配置
     */
    public static class Consumer {
        /** 是否消费消息队列的消息 */
        private Boolean enabled = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * 发布者相关配置
     */
    public static class Publisher {
        /** 是否发布消息到消息队列 */
        private Boolean enabled = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

}
