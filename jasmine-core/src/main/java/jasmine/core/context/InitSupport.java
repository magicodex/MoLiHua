package jasmine.core.context;

/**
 * <p>
 * 可解决循环依赖问题，在所有被管理的对象创建完之后初始。
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
