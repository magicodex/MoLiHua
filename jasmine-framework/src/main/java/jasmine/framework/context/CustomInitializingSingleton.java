package jasmine.framework.context;

import org.springframework.core.Ordered;

/**
 * <p>
 * 在所有 bean 创建完后按顺序调用实现该接口的 bean（类似SmartInitializingSingleton）。
 * </p>
 *
 * @author mh.z
 */
public interface CustomInitializingSingleton extends Ordered {

    /**
     *
     */
    void afterSingletonsInstantiated();
}