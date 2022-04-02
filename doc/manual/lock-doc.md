# 配置

默认是基于 redission 实现，相关配置参考 org.springframework.boot.autoconfigure.data.redis.RedisProperties 以及
org.redisson.spring.starter.RedissonProperties。

# 示例

```
// 使用注解 @DistributedLock 加锁
@DistributedLock(category = "USER", key = "#user.id")
public void updateUser(User user) {
  ...
}
```
