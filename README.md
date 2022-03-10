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

# 是否启用 XXL-job 任务调度（默认false）
jasmine.xxl.job.enabled=true
# XXL-job 配置
xxl.job.admin.addresses=http://127.0.0.1:9090/xxl-job-admin
xxl.job.accessToken=
xxl.job.executor.appname=xxl-job-executor-sample
xxl.job.executor.address=
xxl.job.executor.ip=
xxl.job.executor.port=9999
xxl.job.executor.logpath=/data/applogs/xxl-job/jobhandler
xxl.job.executor.logretentiondays=30

# 是否启用读写分离（默认false）
jasmine.datasource.readWrite.enabled=true
# 只读数据源配置
spring.datasource.read.url=数据库连接字符串
spring.datasource.read.username=用户名
spring.datasource.read.password=密码
```

## 已有功能

- 分布式缓存
- 分布式锁
- 消息队列
- 任务调度
- 读写分离
- 基于角色的访问控制
- 多线程工具类
- 单元测试支持
- 多语言支持
- 多租户支持
- 数据权限支持

备注：目前已有功能的技术设计，包括接口和类设计相对成熟可靠，具体的代码实现仍需通过完善单测用例逐步稳定。

## 集成框架

| 框架 | 版本 | 说明 |
| :----: | :---- | :---- |
| Spring Boot | 2.5.4 | WEB框架 |
| Spring Security | 5.5.2 | 安全框架 |
| Swagger | 2.10.5 | API文档 |
| SLF4J | 1.7.32 | 日志库 |
| Jackson | 2.12.4 | JSON库 |
| Mybatis-Plus | 3.4.3.4 | 持久层框架 |
| HikariCP | 4.0.3 | 数据库连接池 |
| mysql-connector-java | 8.0.28 | MySql JDBC驱动 |
| Liquibase | 4.3.5 | 数据库版本管理工具 |
| Redisson | 3.12.3 | Redis客户端 |
| amqp-client | 5.12.0 | RabbitMQ客户端 |
| xxl-job | 2.3.0 | 任务调度 |
| JUnit | 4.13.2 | 单元测试 |
| spring-test | 5.3.8 | 单元测试 |
| mockito | 4.2.0 | 单元测试 |

## 模块划分

| 目录 | 说明 |
| :----: | :---- |
| jasmine-bom | 依赖管理 |
| jasmine-core | 基本模块 |
| jasmine-framework | 框架模块 |
| jasmine-test | 支持单元测试 |
| jasmine-security | 安全模块 |
| jasmine-security-test | 支持单元测试 |
| jasmine-spring-boot-autoconfigure | 自动配置 |
| jasmine-spring-boot-starter | 自动配置 |
| jasmine-demo | DEMO模块 |


