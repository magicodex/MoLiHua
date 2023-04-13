package jasmine.framework.test.mockito;

import jasmine.framework.common.util.CheckUtil;
import org.mockito.Mockito;

/**
 * <p>
 * 模拟工具类。
 * </p>
 *
 * @author mh.z
 */
public class MockUtil {

    /**
     * 模拟对象
     *
     * @param classToMock
     * @param mock
     * @param <T>
     * @return
     */
    public static <T> T mock(Class<T> classToMock, CustomMock<T> mock) {
        CheckUtil.notNull(classToMock, "classToMock null");

        CustomAnswer<T> answer = new CustomAnswer<>();
        T target = Mockito.mock(classToMock, answer);

        if (mock != null) {
            answer.mock(target, mock);
        }

        return target;
    }

}
