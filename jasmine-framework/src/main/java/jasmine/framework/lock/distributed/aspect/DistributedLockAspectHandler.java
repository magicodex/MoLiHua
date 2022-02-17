package jasmine.framework.lock.distributed.aspect;

import jasmine.framework.lock.annotation.DistributedLock;
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
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 * 对注解 @DistributedLock 做横切处理。
 * </p>
 *
 * @author mh.z
 */
@Aspect
@Component
public class DistributedLockAspectHandler implements Ordered {
    private static final ExpressionParser EXPRESSION_PARSER;
    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER;

    static {
        EXPRESSION_PARSER = new SpelExpressionParser();
        PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();
    }

    @Around("@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock lock) {
        // 创建解析上下文
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] arguments = joinPoint.getArgs();
        EvaluationContext context = new MethodBasedEvaluationContext(null,
                method, arguments, PARAMETER_NAME_DISCOVERER);

        // 解析表达式
        String key = lock.key();
        Expression expression = EXPRESSION_PARSER.parseExpression(key);
        // 通过表达式获取值
        Object value = expression.getValue(context);

        String category = lock.category();
        // 加锁
        Object result = DistributedLocks.lock(category, value, () -> {
            return joinPoint.proceed();
        });

        return result;
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
