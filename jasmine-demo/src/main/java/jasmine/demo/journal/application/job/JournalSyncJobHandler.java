package jasmine.demo.journal.application.job;

import jasmine.framework.job.JobCurrent;
import jasmine.framework.job.xxljob.AbstractXxlJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@ConditionalOnProperty(value = "app.job.enabled",
        havingValue = "true", matchIfMissing = false)
@Component
public class JournalSyncJobHandler extends AbstractXxlJobExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JournalSyncJobHandler.class);

    @Override
    public void execute(JobCurrent job) {
        // TODO 只是用来测试调度任务
        logger.debug("execute journalSyncJobHandler.");
        job.trace("execute journalSyncJobHandler.");
    }

    @Override
    protected String getJobName() {
        return "journalSyncJobHandler";
    }

}
