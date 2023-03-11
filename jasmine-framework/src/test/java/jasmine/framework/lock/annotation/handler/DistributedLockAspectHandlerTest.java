package jasmine.framework.lock.annotation.handler;

import cn.hutool.core.util.ReflectUtil;
import jasmine.framework.lock.annotation.DistributedLock;
import jasmine.framework.lock.annotation.handler.DistributedLockAspectHandler;
import jasmine.framework.lock.distributed.DistributedLockProvider;
import jasmine.framework.lock.DistributedLockUtil;
import jasmine.framework.lock.testdependency.MockDistributedLockProvider;
import jasmine.framework.lock.testdependency.TestObject1;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Method;

/**
 * @author mhz
 */
public class DistributedLockAspectHandlerTest {
    private DistributedLockProvider prevDistributedLockProvider;

    @Before
    public void setUp() {
        prevDistributedLockProvider = DistributedLockUtil.getProvider();
        DistributedLockUtil.initUtil(null);
    }

    @After
    public void tearDown() {
        DistributedLockUtil.initUtil(prevDistributedLockProvider);
    }

    @Test
    public void test() throws Throwable {
        // 初始 DistributedLocks 工具类
        DistributedLockUtil.initUtil(new MockDistributedLockProvider());

        ProceedingJoinPoint joinPoint = mockProceedingJoinPoint();
        // 获取注解
        Class<?> clazz = TestObject1.class;
        Method method = ReflectUtil.getMethod(clazz,
                "call", Object.class, Object.class);
        DistributedLock lock = method.getAnnotation(DistributedLock.class);

        DistributedLockAspectHandler handler = new DistributedLockAspectHandler();
        Object actual = handler.around(joinPoint, lock);
        Assert.assertEquals("key1:value1", actual);
    }

    /**
     * 模拟 ProceedingJoinPoint 对象
     *
     * @return
     */
    protected ProceedingJoinPoint mockProceedingJoinPoint() throws Throwable {
        ProceedingJoinPoint joinPoint = Mockito.mock(ProceedingJoinPoint.class);

        Class<?> clazz = TestObject1.class;
        Method method = ReflectUtil.getMethod(clazz,
                "call", Object.class, Object.class);

        // 模拟 MethodSignature 对象
        MethodSignature signature = Mockito.mock(MethodSignature.class);
        Mockito.when(signature.getMethod())
                .thenReturn(method);

        Mockito.when(joinPoint.getSignature())
                .thenReturn(signature);

        Mockito.when(joinPoint.getArgs())
                .thenReturn(new Object[]{"key1", "value1"});

        Mockito.when(joinPoint.proceed())
                .thenReturn("key1:value1");

        return joinPoint;
    }

}
