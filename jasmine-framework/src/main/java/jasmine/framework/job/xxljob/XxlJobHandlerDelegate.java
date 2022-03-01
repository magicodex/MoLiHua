package jasmine.framework.job.xxljob;

import com.xxl.job.core.handler.IJobHandler;
import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.context.ContextInitAndClearHelper;
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
        if (QStringUtil.isNotEmpty(tenantIdStr)) {
            Long tenantId = QObjectUtil.parseLong(tenantIdStr);

            CurrentSubject.setSubject(tenantId, null);
        }

        ContextInitAndClearHelper.initThenClear(() -> {
            jobExecutor.execute(current);
        });
    }

}
