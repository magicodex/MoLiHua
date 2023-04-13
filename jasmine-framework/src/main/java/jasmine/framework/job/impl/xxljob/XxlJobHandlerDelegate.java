package jasmine.framework.job.impl.xxljob;

import com.xxl.job.core.handler.IJobHandler;
import jasmine.framework.context.CurrentSubject;
import jasmine.framework.common.util.ObjectUtil;
import jasmine.framework.common.util.StringUtil;
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
