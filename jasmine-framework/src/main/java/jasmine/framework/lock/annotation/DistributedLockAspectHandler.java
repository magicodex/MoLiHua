package jasmine.framework.lock.annotation;

import jasmine.core.util.QCheckUtil;
import jasmine.framework.lock.distributed.DistributedLocks;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * <p>
 * 对注解 @DistributedLock 做横切处理。
 * </p>
 *
 * @author mh.z
 */
@Aspect
public class DistributedLockAspectHandler implements Ordered {
    private static final ExpressionParser EXPRESSION_PARSER;
    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER;

    static {
        EXPRESSION_PARSER = new SpelExpressionParser();
        PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();
    }

    @Around("@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock lock) {
        EvaluationContext context = getEvaluationContext(joinPoint);

        // 解析表达式
        String key = lock.key();
        Expression expression = EXPRESSION_PARSER.parseExpression(key);
        // 通过表达式获取值
        Object value = expression.getValue(context);

        String category = lock.category();
        long waitTime = lock.waitTime();
        // 加锁
        Object result = DistributedLocks.lock(category, value, waitTime, () -> {
            return joinPoint.proceed();
        });

        return result;
    }

    /**
     * 返回执行上下文
     *
     * @param joinPoint
     * @return
     */
    protected EvaluationContext getEvaluationContext(ProceedingJoinPoint joinPoint) {
        QCheckUtil.notNull(joinPoint, "joinPoint null");

        // 创建解析上下文
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] arguments = joinPoint.getArgs();
        EvaluationContext context = new MethodBasedEvaluationContext(null,
                method, arguments, PARAMETER_NAME_DISCOVERER);

        return context;
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
