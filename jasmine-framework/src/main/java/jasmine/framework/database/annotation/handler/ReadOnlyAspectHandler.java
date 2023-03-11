package jasmine.framework.database.annotation.handler;

import jasmine.core.util.ErrorUtil;
import jasmine.framework.database.annotation.ReadOnly;
import jasmine.framework.database.datasource.DataSourceDecideFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

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

    public ReadOnlyAspectHandler(DataSourceDecideFacade dataSourceDecideFacade) {
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
                throw ErrorUtil.sneakyError(e);
            }
        }

        return result;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
