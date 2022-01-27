# MoLiHua

## 启动服务
1. 修改 jasmine-demo/src/main/resources/application.yml 的数据库连接参数。
2. 浏览器输入 http://127.0.0.1:8080 访问。
3. 默认账户和密码是 MoLiHua/123456。
4. 默认连接 Redis 的地址是 127.0.0.1:6379。

## 单元测试
1. 新建测试用的数据库。
2. 修改 jasmine-demo/src/test/resources/test-config.properties 的数据库连接参数。
3. 通过命令 mvn test 或其它途径执行单元测试。

## 配置参数
```
# 是否自动创建交换器和队列 (默认false)
app.message-queue.rabbitmq.auto-declare=true

# 是否发布消息到消息队列 (默认false)
app.message-queue.publisher.enabled=true

# 是否消费消息队列的消息 (默认false)
app.message-queue.consumer.enabled=true
```

## 集成框架
| 框架 | 版本 | 说明 |
| :----: | :---- | :---- |
| Spring Boot | 2.5.4 | WEB框架 |
| Spring Security | 5.5.2 | 安全框架 |
| Swagger | 2.9.2 | API文档 |
| Thymeleaf | 3.0.12 | 模板引擎 |
| Mybatis-Plus | 3.4.3.4 | 持久层框架 |
| HikariCP | 4.0.3 | 数据库连接池 |
| Liquibase | 4.3.5 | 数据库版本管理工具 |
| Redisson | 3.12.3 | Redis客户端 |
| amqp-client | 5.12.0 | RabbitMQ客户端 |
| Jackson | 2.12.4 | JSON库 |
| JUnit | 4.13.2 | 单元测试 |
| SLF4J | 1.7.32 | 日志库 |

## 模块划分
| 目录 | 说明 |
| :----: | :---- |
| config | 配置 |
| framework | 框架 |
| common | 通用模块 |
| authentication | 认证模块 |
| dashboard | 仪表盘模块 |
| example | DEMO模块 |

