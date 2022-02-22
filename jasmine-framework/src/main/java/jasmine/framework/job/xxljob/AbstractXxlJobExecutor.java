package jasmine.framework.job.xxljob;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.IJobHandler;
import jasmine.core.context.CurrentSubject;
import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.job.JobExecutor;

/**
 * @author mh.z
 */
public abstract class AbstractXxlJobExecutor extends IJobHandler implements JobExecutor, InitSupport {
    /** 租户 ID 参数名 */
    private static final String TENANT_ID_PARAM_NAME = "tenantId";

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

        String tenantIdStr = (String) current.getParameter(TENANT_ID_PARAM_NAME);
        if (QStringUtil.isNotEmpty(tenantIdStr)) {
            Long tenantId = QObjectUtil.parseLong(tenantIdStr);

            CurrentSubject.setSubject(tenantId, null);
        }

        execute(current);
    }

}
