package jasmine.framework.context.handler;

import jasmine.core.util.ref.ObjectValue;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class ContextHandlerFacadeBeanTest {
    ObjectValue initMethodCalledFlag;
    ObjectValue clearMethodCalledFlag;

    @Before
    @After
    public void setUpOrTearDown() {
        initMethodCalledFlag = new ObjectValue(false);
        clearMethodCalledFlag = new ObjectValue(false);
    }

    @Test
    public void test() {
        // 模拟 ApplicationContext 对象
        ApplicationContext context = mockApplicationContext();

        ContextHandlerFacadeBean facadeBean = new ContextHandlerFacadeBean();
        facadeBean.setApplicationContext(context);
        // 调用初始方法
        facadeBean.afterSingletonsInstantiated();

        List<ContextHandler> handlerList = facadeBean.getHandlers();
        Assert.assertNotNull(handlerList);
        Assert.assertEquals(1, handlerList.size());

        // 调用 initAllToCurrentThread 方法
        facadeBean.initAllToCurrentThread();
        Assert.assertEquals(true, initMethodCalledFlag.get());
        // 调用 clearAllFromCurrentThread 方法
        facadeBean.clearAllFromCurrentThread();
        Assert.assertEquals(true, clearMethodCalledFlag.get());

        // 调用 copyAllFromCurrentThread 方法
        Collection<ContextSnapshot> snapshotList = facadeBean.copyAllFromCurrentThread();
        Assert.assertNotNull(snapshotList);
        Assert.assertEquals(1, snapshotList.size());
    }

    /**
     * 模拟 ApplicationContext 对象
     *
     * @return
     */
    private ApplicationContext mockApplicationContext() {
        // 模拟 ContextHandler 对象
        ContextHandler handler = Mockito.mock(ContextHandler.class);

        // 调用 init 方法则标记 initMethodCalledFlag 变量
        Mockito.doAnswer((invocation) -> {
            initMethodCalledFlag.set(true);
            return null;
        }).when(handler).init();

        // 调用 clear 方法则标记 clearMethodCalledFlag 变量
        Mockito.doAnswer((invocation) -> {
            clearMethodCalledFlag.set(true);
            return null;
        }).when(handler).clear();

        Mockito.when(handler.copy()).thenReturn(
                Mockito.mock(ContextSnapshot.class));

        Map<String, ContextHandler> handlerMap = Map.of("bean1", handler);
        // 模拟 ApplicationContext 对象
        ApplicationContext context = Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getBeansOfType(ContextHandler.class))
                .thenReturn(handlerMap);

        return context;
    }

}
