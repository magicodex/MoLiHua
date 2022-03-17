package jasmine.core.util;

/**
 * <p>
 * 提供异常相关的方法。
 * </p>
 *
 * @author mh.z
 */
public class QErrorUtil {

    /**
     * 抛出异常，比如以下示例：
     * <pre>{@code
     * try {
     *   ...
     * } catch (Exception e) {
     *   throw QErrorUtil.sneakyError(e);
     * }
     * </p>
     * }</pre>
     *
     * @param throwable
     * @return
     */
    public static RuntimeException sneakyError(Throwable throwable) {
        return sneakyThrow(throwable);
    }

    /**
     * 抛出异常
     *
     * @param throwable
     * @param <T>
     * @return
     * @throws T
     */
    protected static <T extends Throwable> T sneakyThrow(Throwable throwable) throws T {
        throw (T) throwable;
    }

}
