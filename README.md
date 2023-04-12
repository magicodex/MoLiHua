# MoLiHua

![茉莉花](https://s3.bmp.ovh/imgs/2022/09/27/84641e2691bad544.png "茉莉花")

## 启动 DEMO 示例

1. 启动 jasmine-demo/src/main/java/jasmine/demo/DemoApplication。
2. 浏览器输入 http://127.0.0.1:8080 访问。
3. 默认账户和密码是 MoLiHua/123456。
4. 默认连接 Redis 的地址是 127.0.0.1:6379。

### 单元测试覆盖率(2023.3.13)
![单元测试覆盖率](https://s3.bmp.ovh/imgs/2023/03/13/4009f43aecd907cc.png "单元测试覆盖率")

## 使用说明

### 1.添加依赖

```
<jasmine.version>1.0.0</jasmine.version>

<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter</artifactId>
    <version>${jasmine.version}</version>
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
| jasmine-MyBatis | 扩展MyBatis |
| jasmine-framework | 框架相关功能 |
| jasmine-framework-test | 支持单元测试 |
| jasmine-security | 安全模块 |
| jasmine-security-test | 支持单元测试 |
| jasmine-spring-boot-autoconfigure | 自动配置 |
| jasmine-spring-boot-starter | 自动配置 |
| jasmine-spring-boot-starter-security | 自动配置 |
| jasmine-spring-cloud-stream | 整合Spring Cloud Stream |
| jasmine-demo | DEMO示例 |

![模块划分](https://s3.bmp.ovh/imgs/2023/03/13/9e2defcb591869cf.png "模块划分")

## 文档链接

- [jasmine-core 文档](https://github.com/magicodex/MoLiHua/blob/main/jasmine-core/doc.md)
- [jasmine-framework 文档](https://github.com/magicodex/MoLiHua/blob/main/jasmine-framework/doc.md)

## 开源协议

使用 MIT 开源协议，点击 [LICENSE](https://github.com/magicodex/MoLiHua/blob/main/LICENSE) 了解协议详情。
