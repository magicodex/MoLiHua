# 配置

```
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

备注：更多配置查看 jasmine.autoconfigure.framework.middleware.XxlJobProperties。
```

# 示例

```
@Component
public class XxxxJobExecutor implements JobExecutor {

    @Override
    public void execute(JobCurrent job) {
        // TODO 调度任务逻辑
    }

    @Override
    protected String getName() {
        // 调度任务名称
        return "xxxxJobHandler";
    }

}
```
