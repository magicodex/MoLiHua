package jasmine.demo.journal.application.job;

import jasmine.framework.job.JobCurrent;
import jasmine.framework.job.xxljob.AbstractXxlJobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Slf4j
@ConditionalOnProperty(value = "app.job.enabled",
        havingValue = "true", matchIfMissing = false)
@Component
public class JournalSyncJobHandler extends AbstractXxlJobExecutor {

    @Override
    public void execute(JobCurrent job) {
        // TODO 只是用来测试调度任务
        log.debug("execute journalSyncJobHandler.");
        job.trace("execute journalSyncJobHandler.");
    }

    @Override
    protected String getJobName() {
        return "journalSyncJobHandler";
    }

}
