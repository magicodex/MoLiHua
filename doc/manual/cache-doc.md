# 添加依赖

```
<!-- Redis客户端 -->
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson-spring-boot-starter</artifactId>
    <scope>compile</scope>
</dependency>
```

# 使用配置

默认是基于 spring-data-redis 实现，相关配置参考 org.springframework.boot.autoconfigure.data.redis.RedisProperties，以下几个配置仅供参考。

```
# Redis连接地址
spring.redis.host=127.0.0.1
# Redis连接端口
spring.redis.port=6379
```

# 代码示例

```
// 先获取缓存里的用户信息，找不到再查数据库并把用户信息添加到缓存中
Long userId = ...
User user = CacheUtil.get("USER", userId, User.class, () -> {
    return ...
});
```
