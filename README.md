# MoLiHua

![茉莉花](https://s3.bmp.ovh/imgs/2022/09/27/84641e2691bad544.png "茉莉花")

## 启动 DEMO 示例

1. 启动 jasmine-demo/src/main/java/jasmine/demo/DemoApplication。
2. 浏览器输入 http://127.0.0.1:8080 访问。
3. 默认账户和密码是 MoLiHua/123456。
4. 默认连接 Redis 的地址是 127.0.0.1:6379。

### 单元测试覆盖率(2023.11.13)

![单元测试覆盖率](https://s3.bmp.ovh/imgs/2023/11/13/cea870eb92472eea.png "单元测试覆盖率")

## 使用说明

### 1.添加依赖

```
<jasmine.version>1.3.6</jasmine.version>

<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter</artifactId>
    <version>${jasmine.version}</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### 2.参考示例

- [国际化](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/i18n-doc.md)
- [分布式缓存](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/cache-doc.md)
- [分布式锁](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/lock-doc.md)
- [消息队列](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/message-queue-doc.md)
- [任务调度](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/job-doc.md)
- [多线程工具类](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/async-task-doc.md)
- [权限访问控制](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/security-doc.md)
- [扩展Mybatis-Plus](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/mybatis-plus-extension-doc.md)
- [单元测试](https://github.com/magicodex/MoLiHua/blob/main/doc/manual/test-doc.md)

## 集成框架

|       框架        | 版本    | 说明          |
|:---------------:|:------|:------------|
|   Spring Boot   | 2.6.7 | WEB框架       |
| Spring Security | 5.6.3 | 权限认证框架      |
|      SLF4J      | -     | 日志库         |
|     Jackson     | -     | JSON库       |
|  Mybatis-Plus   | -     | 持久层框架       |
|    HikariCP     | -     | 数据库连接池      |
|    Liquibase    | -     | 数据库版本管理工具   |
|    Redisson     | -     | Redis客户端    |
|   amqp-client   | -     | RabbitMQ客户端 |
|     xxl-job     | -     | 任务调度        |
|      JUnit      | -     | 单元测试        |
|   spring-test   | -     | 单元测试        |
|     mockito     | -     | 单元测试        |

## 模块划分

|                  目录                  | 说明                    |
|:------------------------------------:|:----------------------|
|             jasmine-bom              | 依赖管理                  |
|          jasmine-framework           | 框架相关功能                |
|        jasmine-framework-test        | 支持单元测试                |
|           jasmine-security           | 安全模块                  |
|        jasmine-security-test         | 支持单元测试                |
|  jasmine-spring-boot-autoconfigure   | 自动配置                  |
|     jasmine-spring-boot-starter      | 自动配置                  |
| jasmine-spring-boot-starter-security | 自动配置                  |
|     jasmine-spring-cloud-stream      | 整合Spring Cloud Stream |
|             jasmine-demo             | DEMO示例                |

![模块划分](https://s3.bmp.ovh/imgs/2023/11/13/6bace7a53f3715f6.png "模块划分")

## 开源协议

使用 MIT 开源协议，点击 [LICENSE](https://github.com/magicodex/MoLiHua/blob/main/LICENSE) 了解协议详情。
