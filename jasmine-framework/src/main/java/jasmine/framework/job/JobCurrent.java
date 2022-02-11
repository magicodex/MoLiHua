package jasmine.framework.job;

/**
 * @author mh.z
 */
public interface JobCurrent {

    /**
     * 查找参数
     *
     * @param name
     * @return
     */
    Object getParameter(String name);

    /**
     * 设置结果
     *
     * @param success
     * @param text
     */
    void setResult(boolean success, String text);

    /**
     * 记录信息
     *
     * @param text
     */
    void trace(String text);
}
