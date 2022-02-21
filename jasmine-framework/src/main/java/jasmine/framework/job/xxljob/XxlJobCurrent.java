package jasmine.framework.job.xxljob;

import com.xxl.job.core.context.XxlJobHelper;
import jasmine.core.util.QNewUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.job.JobCurrent;

import java.util.Map;

/**
 * @author mh.z
 */
public class XxlJobCurrent implements JobCurrent {
    /** 参数 */
    private Map<String, Object> parameters;
    /** 参数与参数之间的分隔符 */
    private static final String PARAM_AND_PARAM_SEPARATOR = ",";
    /** 参数名与值之间的分隔符 */
    private static final String NAME_AND_VALUE_SEPARATOR = ":";

    @Override
    public Object getParameter(String name) {
        if (parameters != null) {
            String jobParam = XxlJobHelper.getJobParam();


        }

        return getParameter(name);
    }

    /**
     * 初始参数
     *
     * @param jobParam
     */
    protected void initParameters(String jobParam) {
        parameters = QNewUtil.map();

        if (QStringUtil.isEmpty(jobParam)) {
            return;
        }

        // 获取各个参数
        String[] params = jobParam.split(PARAM_AND_PARAM_SEPARATOR, -1);
        if (params.length == 0) {
            return;
        }

        // 获取参数名和值
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            String[] pair = param.split(NAME_AND_VALUE_SEPARATOR, -1);

            if (pair.length >= 2) {
                parameters.put(pair[0], pair[1]);
            } else {
                parameters.put(String.valueOf(i), param);
            }
        }
    }

    @Override
    public Object getParameter(int index) {
        return getParameter(String.valueOf(index));
    }

    @Override
    public void setResult(boolean success) {
        setResult(success, null);
    }

    @Override
    public void setResult(boolean success, String text) {
        if (success) {
            XxlJobHelper.handleSuccess(text);
        } else {
            XxlJobHelper.handleFail(text);
        }
    }

    @Override
    public void trace(String text, Object... args) {
        if (args != null && args.length > 0) {
            text = String.format(text, args);
        }

        XxlJobHelper.log(text);
    }

    @Override
    public void trace(Throwable error, String text, Object... args) {
        trace(text, args);

        XxlJobHelper.log(error);
    }

}
