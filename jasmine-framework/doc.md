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

#### 缓存使用

```
// 先获取缓存里的用户信息，找不到再查数据库并把用户信息添加到缓存中
User user = CacheUtil.get("USER", userId, User.class, () -> {
    return getUserById(userId);
});

备注：默认是基于 spring-data-redis 实现，相关配置参考 
org.springframework.boot.autoconfigure.data.redis.RedisProperties，以下几个配置仅供参考。
# Redis连接地址
spring.redis.host=127.0.0.1
# Redis连接端口
spring.redis.port=6379
```

#### 分布式锁使用

```
// 使用注解 @DistributedLock 加锁
@DistributedLock(category = "USER", key = "#user.id")
public void updateUser(User user) {
  ...
}

备注：默认是基于 redission 实现，相关配置参考 org.springframework.boot.autoconfigure.data.redis.RedisProperties
以及 org.redisson.spring.starter.RedissonProperties。
```

#### 多线程工具类使用

```
// 异步执行任务并获取执行结果
List<Callable> taskList = ...
List<Boolean> resultList = AsyncTaskUtil.asyncAndGet(taskList);

备注：默认是基于 org.springframework.task 实现，相关配置参考 
org.springframework.boot.autoconfigure.task.TaskExecutionProperties。
```

#### 参数校验使用

```
// 以下是示例 SpringMVC 校验数据
public XXXX XXXX(..., Errors errors, ...) {
    ValidationHelper helper = ValidationHelper.create(errors);
    helper.field("param1", "").rejectIfEmpty();
}
```

#### 任务调度使用

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

备注：默认是基于 XXL-JOB实现，相关配置参考 jasmine.autoconfigure.framework.middleware.XxlJobProperties，
以下几个配置仅供参考。
# 是否启用 XXL-job 任务调度（默认false）
jasmine.xxl.job.enabled=true
# XXL-job 配置
xxl.job.admin.addresses=http://127.0.0.1:9090/xxl-job-admin
xxl.job.access-token=
xxl.job.executor.app-name=xxl-job-executor-sample
xxl.job.executor.address=
xxl.job.executor.ip=
xxl.job.executor.port=9999
xxl.job.executor.log-path=/data/applogs/xxl-job/jobhandler
xxl.job.executor.log-retention-days=30
```

#### 消息队列使用

```
// 接收消息
public class XxxxMessageReceiver implements MessageReceiver<XXXX> {
    ...
}

// 发送消息
public class Xxxx {
    @Autowired
    private SendMessageService sendMessageService;
    
    public xxxx xxxx(...) {
        ...
        // 发送类别是 sample 的消息
        sendMessageService.send("sample", null, message);
    }
}

备注：默认是基于 spring-amqp 实现，相关代码参考 jasmine.demo.config.ConsumerConfig、
以及 jasmine.demo.config.RabbitConfig，以下几个配置仅供参考。
# 消息队列实现(默认default)，default是基于 spring-amqp 实现，stream是基于 Spring Cloud Stream实现
jasmine.message-queue.type=default
# 是否发布消息到消息队列 (默认false)
jasmine.message-queue.publisher.enabled=true
# 是否消费消息队列的消息 (默认false)
jasmine.message-queue.consumer.enabled=true
```

#### 读写分离使用
```
// 在控制器方法上加上该注解则表明该方法是只读模式
@ReadOnly
public XXXX XXXX(...) {
    ...
}

备注：若启用读写分离则 spring.datasource.type 设置无效，
目前读写分离的数据源使用 hikari 连接池。以下几个配置仅供参考。
# 是否启用读写分离（默认false)
jasmine.datasource.readWrite.enabled=true
# 只读数据源配置
spring.datasource.read.url=数据库连接字符串
spring.datasource.read.username=用户名
spring.datasource.read.password=密码
```
