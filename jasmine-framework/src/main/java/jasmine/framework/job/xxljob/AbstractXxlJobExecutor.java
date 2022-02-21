package jasmine.framework.job.xxljob;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.IJobHandler;
import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.framework.job.JobExecutor;

/**
 * @author mh.z
 */
public abstract class AbstractXxlJobExecutor extends IJobHandler implements JobExecutor, InitSupport {

    /**
     * 返回调度任务名称
     *
     * @return
     */
    protected abstract String getJobName();

    @Override
    public void init(RuntimeProvider provider) {
        XxlJobExecutor.registJobHandler(getJobName(), this);
    }

    @Override
    public void execute() throws Exception {
        XxlJobCurrent current = new XxlJobCurrent();

        execute(current);
    }

}
