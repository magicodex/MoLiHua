package jasmine.framework.testdependency.mockito;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Method;

/**
 * @author mh.z
 * @param <T>
 */
public class CustomAnswer<T> implements Answer<T> {
    private boolean mockFlag;

    public CustomAnswer() {
        this.mockFlag = false;
    }

    /**
     * 模拟对象
     *
     * @param target
     * @param mock
     */
    public void mock(T target, CustomMock<T> mock) {
        mockFlag = true;

        try {
            mock.mock(target);
        } finally {
            mockFlag = false;
        }
    }

    @Override
    public T answer(InvocationOnMock invocation) throws Throwable {
        if (mockFlag) {
            return null;
        }

        Object object = invocation.getMock();
        String className = object.getClass().getName();
        Method method = invocation.getMethod();
        String methodName = method.getName();

        throw new UnsupportedOperationException(className + "." + methodName + " should be mocked");
    }

}
