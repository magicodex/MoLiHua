package jasmine.demo.journal.application.job;

import jasmine.framework.job.JobCurrent;
import jasmine.framework.job.xxljob.AbstractXxlJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class JournalSyncJobHandler extends AbstractXxlJobExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JournalSyncJobHandler.class);

    @Override
    public void execute(JobCurrent job) {
        logger.debug("execute journalSyncJobHandler.");
        job.trace("execute journalSyncJobHandler.");
    }

    @Override
    protected String getJobName() {
        return "journalSyncJobHandler";
    }

}
