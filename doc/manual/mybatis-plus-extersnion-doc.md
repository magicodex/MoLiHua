# 依赖

```
<!-- MyBatis-Plus持久层框架 -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <scope>compile</scope>
</dependency>
```

# 配置

```
# 配置数据源示例
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/sample
spring.datasource.username=root
spring.datasource.password=123456

# 启动类上加上 @MapperScan 注解的示例（自定义 mapper 接口类的扫描路径）
@MapperScan("sample.**.mapper")

# 自定义 mapper 文件路径的示例
mybatis-plus.mapper-locations=sample/**/*.xml

# 是否启用租户拦截器 (默认false)
jasmine.data.tenant.enabled=true

# 配置数据加密
jasmine.crypto.password=密码
jasmine.crypto.sale=盐值
```
