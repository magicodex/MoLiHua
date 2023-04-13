package jasmine.framework.job.impl.xxljob;

import com.xxl.job.core.context.XxlJobContext;
import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.SubjectProvider;
import jasmine.framework.common.util.ref.ObjectValue;
import jasmine.framework.context.thread.ContextManagementUtil;
import jasmine.framework.context.thread.ContextHandlerFacade;
import jasmine.framework.job.JobCurrent;
import jasmine.framework.job.JobExecutor;
import jasmine.framework.testdependency.mock.MockSubjectProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class XxlJobHandlerDelegateTest {
    private XxlJobContext prevXxlJobContext;
    private SubjectProvider prevSubjectProvider;
    private ContextHandlerFacade prevContextHandlerFacade;

    private ObjectValue jobExecutorExecuteMethodCalledFlag;

    @Before
    public void setUp() {
        prevXxlJobContext = XxlJobContext.getXxlJobContext();
        XxlJobContext.setXxlJobContext(null);

        prevSubjectProvider = CurrentSubject.getSubjectProvider();
        CurrentSubject.initUtil(null);

        prevContextHandlerFacade = ContextManagementUtil.getHandlerFacade();
        ContextManagementUtil.initUtil(null);

        jobExecutorExecuteMethodCalledFlag = new ObjectValue(false);
    }

    @After
    public void tearDown() {
        XxlJobContext.setXxlJobContext(prevXxlJobContext);
        CurrentSubject.initUtil(prevSubjectProvider);
        ContextManagementUtil.initUtil(prevContextHandlerFacade);

        jobExecutorExecuteMethodCalledFlag = new ObjectValue(false);
    }

    @Test
    public void test() throws Exception {
        // 初始 XxlJobContext 的上下文
        XxlJobContext jobContext = new XxlJobContext(1L, "param1:value1,tenantId:666666",
                "", -1, -1);
        XxlJobContext.setXxlJobContext(jobContext);
        // 初始 CurrentSubject 的上下文
        CurrentSubject.initUtil(new MockSubjectProvider());
        // 初始 ContextManagementHelper 的上下文
        ContextManagementUtil.initUtil(Mockito.mock(ContextHandlerFacade.class));

        JobExecutor executor = mockJobExecutor();
        XxlJobHandlerDelegate delegate = new XxlJobHandlerDelegate(executor);
        delegate.execute();
        Assert.assertEquals(true, jobExecutorExecuteMethodCalledFlag.get());

        // 检查当前的租户ID
        Assert.assertEquals(Long.valueOf(666666), CurrentSubject.getTenantId());
    }

    /**
     * 模拟 JobExecutor 对象
     *
     * @return
     */
    private JobExecutor mockJobExecutor() {
        JobExecutor executor = new JobExecutor() {
            @Override
            public String getName() {
                return "testJobExecutor";
            }

            @Override
            public void execute(JobCurrent job) {
                jobExecutorExecuteMethodCalledFlag.set(true);
            }
        };

        return executor;
    }

}
