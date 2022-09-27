# MoLiHua

![Jasmine](https://s3.bmp.ovh/imgs/2022/09/27/84641e2691bad544.png "Jasmine")

## Quick Start
1. Modify the database connection parameters of jasmine-demo/src/main/resources/application.yml.
2. Enter http://127.0.0.1:8080 in the browser to access.
3. The default account and password is MoLiHua/123456.
4. The default address for connecting to Redis is 127.0.0.1:6379.

## Maven dependency

```
<jasmine.version>1.0.0</jasmine.version>

<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter</artifactId>
    <version>${jasmine.version}</version>
</dependency>
```

## Integration

| Framework | Version | Description |
| :----: | :---- | :---- |
| Spring Boot | 2.6.7 |  |
| Spring Security | 5.6.3 |  |
| SLF4J | - |  |
| Jackson | - |  |
| Mybatis-Plus | 3.4.3.4 |  |
| HikariCP | - |  |
| Liquibase | 4.3.5 |  |
| Redisson | 3.12.3 |  |
| amqp-client | 5.13.1 |  |
| xxl-job | 2.3.0 |  |
| JUnit | - |  |
| spring-test | 5.3.19 |  |
| mockito | 4.2.0 |  |

## Module

| Module | Description |
| :----: | :---- |
| jasmine-bom |  |
| jasmine-core |  |
| jasmine-core-test | unit test support |
| jasmine-framework |  |
| jasmine-framework-test | unit test support |
| jasmine-security |  |
| jasmine-security-test | unit test support |
| jasmine-spring-boot-autoconfigure |  |
| jasmine-spring-boot-starter |  |
| jasmine-spring-boot-starter-security |  |
| jasmine-spring-cloud-stream |  |
| jasmine-demo | DEMO |

![Module](https://s3.bmp.ovh/imgs/2022/09/27/fd72e1da6c00119e.png "Module")

## License

MoLiHua is under the MIT license. 
See the [LICENSE](https://github.com/magicodex/MoLiHua/blob/main/LICENSE) file for details.