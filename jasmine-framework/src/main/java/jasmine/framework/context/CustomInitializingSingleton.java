package jasmine.framework.context;

/**
 * <p>
 * 在所有 bean 创建完后按顺序调用实现该接口的 bean（类似SmartInitializingSingleton）。
 * </p>
 *
 * @author mh.z
 */
public interface CustomInitializingSingleton {

    /**
     * 调用方法
     */
    void afterSingletonsInstantiated();

    /**
     * 返回序号
     *
     * @return
     */
    int getOrder();
}