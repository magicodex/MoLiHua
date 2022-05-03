# 配置

默认是基于 spring-amqp 实现，相关代码参考 jasmine.demo.config.ConsumerConfig 以及 jasmine.demo.config.RabbitConfig，以下几个配置仅供参考。

```
# 消息队列实现(默认default)，default是基于 spring-amqp 实现，stream是基于 Spring Cloud Stream 实现
jasmine.message-queue.type=default

# 是否发布消息到消息队列 (默认false)
jasmine.message-queue.publisher.enabled=true

# 是否消费消息队列的消息 (默认false)
jasmine.message-queue.consumer.enabled=true

# 使用 RabbitMQ 的配置参考
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=用户名
spring.rabbitmq.password=密码
```

# 示例

```
// 接收消息
@Component
public class SampleMessageReceiver implements MessageReceiver<Sample> {
 
    @Override
    public Class<Sample> getType() {
        return Sample.class;
    }

    @Override
    public void receive(Sample sample) {
        ...
    }
}

// 发送消息
SendMessageService sendMessageService = ...
Object message = ...
sendMessageService.send("sample", null, message);

```
