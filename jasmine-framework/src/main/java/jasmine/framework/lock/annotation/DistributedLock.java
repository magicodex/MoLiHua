package jasmine.framework.lock.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mh.z
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    /**
     * 类别
     *
     * @return
     */
    String category();

    /**
     * 锁key
     *
     * @return
     */
    String key();

    /**
     * 等待时间（单位毫秒）
     *
     * @return
     */
    long waitTime() default 10000L;
}
