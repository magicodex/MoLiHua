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
jasmine.message-queue.publisher.enabled=true

# 是否消费消息队列的消息 (默认false)
jasmine.message-queue.consumer.enabled=true

# 是否启用RBAC访问控制 (默认false)
jasmine.security.rbac.enabled=true
```

## 集成框架
| 框架 | 版本 | 说明 |
| :----: | :---- | :---- |
| Spring Boot | 2.5.4 | WEB框架 |
| Spring Security | 5.5.2 | 安全框架 |
| SLF4J | 1.7.32 | 日志库 |
| Swagger | 2.10.5 | API文档 |
| Thymeleaf | 3.0.12 | 模板引擎 |
| Mybatis-Plus | 3.4.3.4 | 持久层框架 |
| HikariCP | 4.0.3 | 数据库连接池 |
| mysql-connector-java | 8.0.28 | MySql JDBC驱动 |
| Liquibase | 4.3.5 | 数据库版本管理工具 |
| Redisson | 3.12.3 | Redis客户端 |
| amqp-client | 5.12.0 | RabbitMQ客户端 |
| xxl-job | 2.3.0 | 调度任务 |
| Jackson | 2.12.4 | JSON库 |
| JUnit | 4.13.2 | 单元测试 |
| spring-test | 5.3.8 | 单元测试 |
| mockito-core | 4.2.0 | 单元测试 |

## 模块划分
| 目录 | 说明 |
| :----: | :---- |
| jasmine-core | 基本模块 |
| jasmine-framework | 框架模块 |
| jasmine-test | 测试模块 |
| jasmine-security | 安全模块 |
| jasmine-demo | DEMO模块 |


