package jasmine.core.util.timer;

/**
 * @author mh.z
 */
public class ExecuteTimer {
    /** 开始时间 */
    private Long startTime;
    /** 结束时间 */
    private Long endTime;
    /** 总的时间 */
    private Long totalTime;

    private ExecuteTimer(Long startTime) {
        this.startTime = startTime;
        this.endTime = -1L;
        this.totalTime = -1L;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    /**
     * 开始计时
     *
     * @return
     */
    public static ExecuteTimer start() {
        return new ExecuteTimer(System.currentTimeMillis());
    }

    /**
     * 停止计时
     *
     * @return
     */
    public long stop() {
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;

        return totalTime;
    }

    /**
     * 重新计时
     */
    public void restart() {
        startTime = System.currentTimeMillis();
        endTime = -1L;
        totalTime = -1L;
    }

    /**
     * 停止计时后再开始
     *
     * @return
     */
    public long stopAndRestart() {
        // 停止计时
        long result = stop();
        // 重新计时
        restart();

        return result;
    }

}
