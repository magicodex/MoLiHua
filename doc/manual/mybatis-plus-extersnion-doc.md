# 配置

```
# 是否启用租户拦截器 (默认false)
jasmine.data.tenant.enabled=true

# 配置数据源示例
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/sample
spring.datasource.username=root
spring.datasource.password=123456

# 自定义 mapper 文件路径示例
mybatis-plus.mapper-locations=sample/**/*.xml
```
