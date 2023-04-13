package jasmine.framework.context.testdependency;

import jasmine.framework.context.CustomInitializingSingleton;

/**
 * @author mh.z
 */
public class MockCustomInitializingSingleton implements CustomInitializingSingleton {
    private int order;
    private Boolean instantiatedMethodCalledFlag;
    private int instantiatedMethodCalledOrder;

    private static int instantiatedMethodCalledCount = 0;

    public MockCustomInitializingSingleton(int order) {
        this.order = order;
        this.instantiatedMethodCalledFlag = false;
        this.instantiatedMethodCalledOrder = 0;
    }

    public Boolean getInstantiatedMethodCalledFlag() {
        return instantiatedMethodCalledFlag;
    }

    public int getInstantiatedMethodCalledOrder() {
        return instantiatedMethodCalledOrder;
    }

    @Override
    public void afterSingletonsInstantiated() {
        instantiatedMethodCalledFlag = true;
        instantiatedMethodCalledCount = instantiatedMethodCalledCount + 1;
        instantiatedMethodCalledOrder = instantiatedMethodCalledCount;
    }

    @Override
    public int getOrder() {
        return order;
    }

}
