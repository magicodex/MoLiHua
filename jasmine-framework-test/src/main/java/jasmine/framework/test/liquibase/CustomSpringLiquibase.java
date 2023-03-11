package jasmine.framework.test.liquibase;

import jasmine.core.util.ErrorUtil;
import jasmine.core.context.CustomInitializingSingleton;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

/**
 * @author mh.z
 */
public class CustomSpringLiquibase extends SpringLiquibase implements CustomInitializingSingleton {

    @Override
    public void afterPropertiesSet() throws LiquibaseException {
        //
    }

    @Override
    public void afterSingletonsInstantiated() {
        try {
            super.afterPropertiesSet();
        } catch (LiquibaseException e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
