# MoLiHua
![茉莉花](./blob/main/logo.png "茉莉花")

## 启动服务

1. 修改 jasmine-demo/src/main/resources/application.yml 的数据库连接参数。
2. 浏览器输入 http://127.0.0.1:8080 访问。
3. 默认账户和密码是 MoLiHua/123456。
4. 默认连接 Redis 的地址是 127.0.0.1:6379。

## 单元测试

1. 新建测试用的数据库。
2. 修改 jasmine-demo/src/test/resources/test-config.properties 的数据库连接参数。
3. 通过命令 mvn test 或其它途径执行单元测试。

## 使用说明

```
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter</artifactId>
    <version>最新版本</version>
</dependency>

<!-- 可选，权限认证框架 -->
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter-security</artifactId>
    <version>最新版本</version>
</dependency>

<!-- 可选，支持单元测试 -->
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-framework-test</artifactId>
    <version>最新版本</version>
    <scope>test</scope>
</dependency>

<!-- 可选，支持单元测试 -->
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-security-test</artifactId>
    <version>最新版本</version>
    <scope>test</scope>
</dependency>
```

## 参数配置

```
# 是否自动创建交换器和队列 (默认false)
app.message-queue.rabbitmq.auto-declare=true
# 是否发布消息到消息队列 (默认false)
jasmine.message-queue.publisher.enabled=true
# 是否消费消息队列的消息 (默认false)
jasmine.message-queue.consumer.enabled=true

# 是否启用RBAC访问控制 (默认default)
jasmine.security.authorization.strategy=rbac

# 是否启用租户拦截器 (默认false)
jasmine.data.tenant.enabled=true

# 是否启用 XXL-job 任务调度（默认false）
jasmine.xxl.job.enabled=true
# XXL-job 配置
xxl.job.admin.addresses=http://127.0.0.1:9090/xxl-job-admin
xxl.job.access-token=
xxl.job.executor.app-name=xxl-job-executor-sample
xxl.job.executor.address=
xxl.job.executor.ip=
xxl.job.executor.port=9999
xxl.job.executor.log-path=/data/applogs/xxl-job/jobhandler
xxl.job.executor.log-retention-days=30

# 是否启用读写分离（默认false）
jasmine.datasource.readWrite.enabled=true
# 只读数据源配置
spring.datasource.read.url=数据库连接字符串
spring.datasource.read.username=用户名
spring.datasource.read.password=密码
```

## 文档链接

- [jasmine-core 文档](./blob/main/jasmine-core/doc.md)
- [jasmine-framework 文档](./blob/main/jasmine-framework/doc.md)

## 已有功能

- 分布式缓存
- 分布式锁
- 消息队列
- 任务调度
- 读写分离
- 基于角色的访问控制
- 多线程并发工具类
- 单元测试支持
- 多语言支持
- 多租户支持
- 数据权限支持

## 集成框架

| 框架 | 版本 | 说明 |
| :----: | :---- | :---- |
| Spring Boot | 2.5.4 | WEB框架 |
| Spring Security | 5.5.2 | 权限认证框架 |
| SLF4J | 1.7.32 | 日志库 |
| Jackson | 2.12.4 | JSON库 |
| Mybatis-Plus | 3.4.3.4 | 持久层框架 |
| HikariCP | 4.0.3 | 数据库连接池 |
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
| jasmine-test | 支持单元测试 |
| jasmine-framework | 框架模块 |
| jasmine-framework-test | 支持单元测试 |
| jasmine-security | 安全模块 |
| jasmine-security-test | 支持单元测试 |
| jasmine-spring-boot-autoconfigure | 自动配置 |
| jasmine-spring-boot-starter | 自动配置 |
| jasmine-spring-boot-starter-security | 自动配置 |
| jasmine-demo | DEMO模块 |

![模块划分](./blob/main/MODULE.png "模块划分")

## 开源协议

使用 MIT 开源协议，点击 [LICENSE](./blob/main/LICENSE) 了解协议详情。
