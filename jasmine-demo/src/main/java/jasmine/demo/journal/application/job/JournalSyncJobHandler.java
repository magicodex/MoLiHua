package jasmine.demo.journal.application.job;

import jasmine.framework.job.JobCurrent;
import jasmine.framework.job.JobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Slf4j
@Component
public class JournalSyncJobHandler implements JobExecutor {

    @Override
    public String getName() {
        return "journalSyncJobHandler";
    }

    @Override
    public void execute(JobCurrent job) {
        // TODO 只是用来测试调度任务
        log.debug("execute journalSyncJobHandler.");
        job.trace("execute journalSyncJobHandler.");
    }

}
