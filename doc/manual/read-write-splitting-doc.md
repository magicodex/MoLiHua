# 使用配置

```
# 是否启用读写分离（默认false)
jasmine.datasource.readWrite.enabled=true

# 只读数据源配置
spring.datasource.read.url=数据库连接字符串
spring.datasource.read.username=用户名
spring.datasource.read.password=密码

备注：1.若启用读写分离则 spring.datasource.type 设置无效，目前读写分离的数据源使用 hikari 连接池。
2.若要改用其它数据源则可注册 mainDataSource、readDataSource 覆盖默认的 bean。
```

# 代码示例

```
// 在控制器方法上加上该注解则表明该方法是只读模式
@ReadOnly
public String sample(...) {
    ...
}
```
