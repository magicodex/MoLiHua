package jasmine.framework.context;

/**
 * <p>
 * 可以解决 IOC 循环依赖问题，在所有被管理的对象创建完之后初始。
 * </p>
 *
 * @author mh.z
 */
public interface InitSupport {

    /**
     * 初始
     *
     * @param provider
     */
    void init(RuntimeProvider provider);
}
