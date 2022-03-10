### 模块

| 名称 | 说明 |
| --- | --- |
| common | 通用模块 |
| context | 上下文 |
| i18n | 多语言支持 |
| validation | 校验 |
| web | 提供 WEB 相关帮助类 |
| cache | 缓存 |
| concurrent | 支持异步等 |
| lock | 锁 |
| job | 调度 |
| persistence | 扩展持久化层 |
| remote/mq | 消息队列 |

#### 缓存 cache 示例

```
// 先获取缓存里的用户信息，找不到再查数据库并把用户信息添加到缓存中
User user = CacheUtil.get("USER", userId, User.class, () -> {
    return getUserById(userId);
});
```

#### 锁 lock 示例

```
// 使用注解 @DistributedLock 加锁
@DistributedLock(category = "USER", key = "#user.id")
public void updateUser(User user) {
  ...
}
```

#### 调度 job 示例

```
@Component
public class UserSyncJobExecutor implements JobExecutor {

    @Override
    public void execute(JobCurrent job) {
        // TODO 调度任务逻辑
    }

    @Override
    protected String getName() {
        // 调度任务名称
        return "userSyncJobHandler";
    }

}
```
