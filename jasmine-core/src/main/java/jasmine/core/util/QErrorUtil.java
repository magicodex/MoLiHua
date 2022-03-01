package jasmine.core.util;

/**
 * @author mh.z
 */
public class QErrorUtil {

    /**
     * 抛出异常
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
