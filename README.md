# MoLiHua

![茉莉花](https://github.com/magicodex/MoLiHua/blob/main/logo.png "茉莉花")

## 启动 DEMO 示例

1. 修改 jasmine-demo/src/main/resources/application.yml 的数据库连接参数。
2. 浏览器输入 http://127.0.0.1:8080 访问。
3. 默认账户和密码是 MoLiHua/123456。
4. 默认连接 Redis 的地址是 127.0.0.1:6379。

## 单元测试

```
通过命令 mvn test 或其它途径执行单元测试。

备注：目前已经内嵌H2数据库做单元测试，若改用其它数据库做单元测试则可以指定数据库连接参数，
比如 mvn test -Ddb.driver=com.mysql.cj.jdbc.Driver -Ddb.url=jdbc:mysql://127.0.0.1:3306/test_jasmine -Ddb.username=root -Ddb.password=123456

#JDBC驱动，默认org.h2.Driver
-Ddb.driver= 
#数据库连接URL，默认jdbc:h2:mem:test_jasmine;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false
-Ddb.url=
#数据库用户名
-Ddb.username=
#数据库密码
-Ddb.password=
```

## 使用说明

### 1.添加依赖

```
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter</artifactId>
    <version>最新版本</version>
</dependency>
```

### 2.参考示例

- [国际化](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/i18n-doc.md)
- [分布式缓存](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/cache-doc.md)
- [分布式锁](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/lock-doc.md)
- [消息队列](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/message-queue-doc.md)
- [任务调度](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/job-doc.md)
- [多线程工具类](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/async-task-doc.md)
- [校验工具类](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/validation-doc.md)
- [权限访问控制](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/security-doc.md)
- [读写分离](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/read-write-splitting-doc.md)
- [扩展Mybatis-Plus](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/mybatis-plus-extersnion-doc.md)
- [单元测试](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/test-doc.md)

## 集成框架

| 框架 | 版本 | 说明 |
| :----: | :---- | :---- |
| Spring Boot | 2.6.7 | WEB框架 |
| Spring Security | 5.6.3 | 权限认证框架 |
| SLF4J | - | 日志库 |
| Jackson | - | JSON库 |
| Mybatis-Plus | 3.4.3.4 | 持久层框架 |
| HikariCP | - | 数据库连接池 |
| Liquibase | 4.3.5 | 数据库版本管理工具 |
| Redisson | 3.12.3 | Redis客户端 |
| amqp-client | 5.13.1 | RabbitMQ客户端 |
| xxl-job | 2.3.0 | 任务调度 |
| JUnit | - | 单元测试 |
| spring-test | 5.3.19 | 单元测试 |
| mockito | 4.2.0 | 单元测试 |

## 模块划分

| 目录 | 说明 |
| :----: | :---- |
| jasmine-bom | 依赖管理 |
| jasmine-core | 公共模块 |
| jasmine-test | 支持单元测试 |
| jasmine-framework | 框架相关功能 |
| jasmine-framework-test | 支持单元测试 |
| jasmine-security | 安全模块 |
| jasmine-security-test | 支持单元测试 |
| jasmine-spring-boot-autoconfigure | 自动配置 |
| jasmine-spring-boot-starter | 自动配置 |
| jasmine-spring-boot-starter-security | 自动配置 |
| jasmine-spring-cloud-stream | 整合Spring Cloud Stream |
| jasmine-demo | DEMO示例 |

![模块划分](https://github.com/magicodex/MoLiHua/blob/main/MODULE.png "模块划分")

## 文档链接

- [jasmine-core 文档](https://github.com/magicodex/MoLiHua/blob/main/jasmine-core/doc.md)
- [jasmine-framework 文档](https://github.com/magicodex/MoLiHua/blob/main/jasmine-framework/doc.md)

## 开源协议

使用 MIT 开源协议，点击 [LICENSE](https://github.com/magicodex/MoLiHua/blob/main/LICENSE) 了解协议详情。
