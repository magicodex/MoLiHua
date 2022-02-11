package jasmine.framework.job;

/**
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
