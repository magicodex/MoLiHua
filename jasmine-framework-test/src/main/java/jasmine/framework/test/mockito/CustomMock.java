package jasmine.framework.test.mockito;

/**
 * 自定义模拟
 *
 * @author mh.z
 * @param <T>
 */
public interface CustomMock<T> {

    /**
     * 模拟对象
     *
     * @param t
     */
    void mock(T t);
}
