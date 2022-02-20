package jasmine.demo.journal.application.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author mh.z
 */
@Component
public class journalSyncJobHandler {

    @XxlJob("journalSyncJobHandler")
    public void journalSyncJobHandler() throws Exception {
        XxlJobHelper.log("execute journalSyncJobHandler.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(1);
        }
        // default success
    }

}
