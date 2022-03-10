package jasmine.framework.job;

/**
 * <p>
 * 当前任务。
 * </p>
 *
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
     * 查找参数
     *
     * @param index
     * @return
     */
    Object getParameter(int index);


    /**
     * 设置结果
     *
     * @param success
     */
    void setResult(boolean success);

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
     * @param args
     */
    void trace(String text, Object... args);

    /**
     * 记录信息
     *
     * @param error
     * @param text
     * @param args
     */
    void trace(Throwable error, String text, Object... args);
}
