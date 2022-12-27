# 公共配置

```
# 消息队列实现(默认default)，default是基于 spring-amqp 实现，stream是基于 Spring Cloud Stream 实现
jasmine.message-queue.type=default

# 是否发布消息到消息队列 (默认false)
jasmine.message-queue.publisher.enabled=true

# 是否消费消息队列的消息 (默认false)
jasmine.message-queue.consumer.enabled=true
```

# spring-amqp版配置示例（RabbitMQ）

```
<!-- 添加依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
    <scope>compile</scope>
</dependency>

spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=用户名
spring.rabbitmq.password=密码

备注：消费者配置参考 jasmine.demo.config.ConsumerConfig,
交换器以及队列等配置参考 jasmine.demo.config.RabbitConfig。
```

# Spring Cloud Stream版配置示例（RabbitMQ）

```
<!-- 添加依赖 -->
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-cloud-stream</artifactId>
    <version>最新版本</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
</dependency>

# 配置 Spring Cloud Stream 
spring.cloud.stream.bindings.sample-out-0.destination=sampleExchange
spring.cloud.stream.bindings.sample-out-0.content-type=application/json
spring.cloud.stream.bindings.sample-out-0.binder=sample
spring.cloud.stream.bindings.sample-in-0.destination=sampleExchange
spring.cloud.stream.bindings.sample-in-0.content-type=application/json
spring.cloud.stream.bindings.sample-in-0.binder=sample
spring.cloud.stream.binders.sample.type=rabbit
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=用户名
spring.rabbitmq.password=密码

// 配置消费者
@Configuration
public class ConsumerConfig {
    @Autowired
    private ReceiveMessageService receiveMessageService;

    @Bean
    public Consumer<Message<Sample>> sample() {
        return message -> {
          receiveMessageService.receive("sample", message);
        };
    }
}
```

# 使用示例

```
// 接收消息，要求 bean 名是 MessageReceiver 作为后缀
@Component
public class SampleMessageReceiver implements MessageReceiver<Sample> {
 
    @Override
    public Class<Sample> getType() {
        return Sample.class;
    }

    @Override
    public void receive(Sample sample) {
        // TODO 消息处理逻辑
    }
}

// 发送消息
SendMessageService sendMessageService = ...
Object message = ...
sendMessageService.send("sample", null, message);

```