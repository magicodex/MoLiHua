package jasmine.framework.i18n;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 声明多语言。
 * </p>
 *
 * @author mh.z
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeclareI18N {

    /**
     * 描述
     *
     * @return
     */
    String value();
}
