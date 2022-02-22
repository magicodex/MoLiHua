package jasmine.framework.persistence.datasource.aspect;

import jasmine.framework.persistence.annotation.ReadOnly;
import jasmine.framework.persistence.datasource.DataSourceDecideFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 对注解 @ReadOnly 做横切处理。
 * </p>
 *
 * @author mh.z
 */
@Aspect
public class ReadOnlyAspectHandler implements Ordered {
    private DataSourceDecideFacade dataSourceDecideFacade;

    public ReadOnlyAspectHandler(@Autowired(required = false) DataSourceDecideFacade dataSourceDecideFacade) {
        this.dataSourceDecideFacade = dataSourceDecideFacade;
    }

    @Around("@annotation(readOnly)")
    public Object around(ProceedingJoinPoint joinPoint, ReadOnly readOnly) {
        Object result = null;

        if (dataSourceDecideFacade != null) {
            result = dataSourceDecideFacade.readOny(() -> {
                return joinPoint.proceed();
            });
        } else {
            try {
                result = joinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
