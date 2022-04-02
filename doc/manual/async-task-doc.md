# 配置

默认是基于 org.springframework.task 实现，相关配置参考 
org.springframework.boot.autoconfigure.task.TaskExecutionProperties。

# 示例

```
// 异步执行任务并获取执行结果
List<Callable> taskList = ...
List<Boolean> resultList = AsyncTaskUtil.asyncAndGet(taskList);
```