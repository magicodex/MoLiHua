package jasmine.framework.job.xxljob;

import com.xxl.job.core.handler.IJobHandler;
import jasmine.core.context.CurrentSubject;
import jasmine.core.util.ObjectUtil;
import jasmine.core.util.StringUtil;
import jasmine.framework.context.thread.ContextManagementUtil;
import jasmine.framework.job.JobExecutor;

/**
 * @author mh.z
 */
public class XxlJobHandlerDelegate extends IJobHandler {
    private JobExecutor jobExecutor;

    /** 租户 ID 参数名 */
    private static final String TENANT_ID_PARAM_NAME = "tenantId";

    public XxlJobHandlerDelegate(JobExecutor jobExecutor) {
        this.jobExecutor = jobExecutor;
    }

    @Override
    public void execute() throws Exception {
        XxlJobCurrent current = new XxlJobCurrent();
        String tenantIdStr = (String) current.getParameter(TENANT_ID_PARAM_NAME);

        // 初始安全上下文
        if (StringUtil.isNotEmpty(tenantIdStr)) {
            Long tenantId = ObjectUtil.parseLong(tenantIdStr);

            CurrentSubject.setSubject(tenantId, null);
        }

        ContextManagementUtil.manageContext(() -> {
            jobExecutor.execute(current);
        });
    }

}
