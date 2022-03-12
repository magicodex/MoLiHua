package jasmine.demo.sample.job;

import jasmine.framework.job.JobCurrent;
import jasmine.framework.job.JobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class SampleJobHandler implements JobExecutor {
    private static Logger logger = LoggerFactory.getLogger(SampleJobHandler.class);

    @Override
    public String getName() {
        return "sampleJobHandler";
    }

    @Override
    public void execute(JobCurrent job) {
        logger.info("job(sampleJobHandler) execute.");
    }

}
