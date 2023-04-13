package jasmine.framework.i18n;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 在接口类字段上声明多语言，比如以下示例：
 * <pre>{@code
 * public interface XXXXMessages {
 *     @DeclareI18N("你好，世界！")
 *     String HELLO_WORLD = "helloWorld";
 * }
 * </p>
 * }</pre>
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
