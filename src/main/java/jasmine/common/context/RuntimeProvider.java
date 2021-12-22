package jasmine.common.context;

/**
 * <p>
 * 提供根据类型获取对象的方法。
 * </p>
 *
 * @author mh.z
 */
public interface RuntimeProvider {

    /**
     * 返回指定类型的对象
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> T getByType(Class<T> type);

    /**
     * 返回指定名称的对象
     *
     * @param name
     * @param <T>
     * @return
     */
    <T> T getByName(String name);
}
