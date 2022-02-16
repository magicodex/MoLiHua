package jasmine.framework.job;

/**
 * <p>
 * 调度接口。
 * </p>
 *
 * @author mh.z
 */
public interface JobService {

    /**
     * 执行
     *
     * @param job
     */
    void execute(JobCurrent job);
}
