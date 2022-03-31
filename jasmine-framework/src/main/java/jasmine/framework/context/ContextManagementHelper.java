package jasmine.framework.context;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QErrorUtil;
import jasmine.framework.common.util.function.FunctionWithResult;
import jasmine.framework.common.util.function.FunctionWithoutResult;
import jasmine.framework.context.handler.ContextHandlerFacade;

/**
 * <p>
 * 提供帮助类用于初始和清理线程变量。
 * </p>
 *
 * @author mh.z
 */
public class ContextManagementHelper {
    private static ContextHandlerFacade handlerFacade;

    public static void initUtil(ContextHandlerFacade handlerFacade) {
        ContextManagementHelper.handlerFacade = handlerFacade;
    }

    /**
     * 初始然后清理
     *
     * @param function
     * @param <T>
     * @return
     */
    public static <T> T initThenClear(FunctionWithResult<T> function) {
        QCheckUtil.notNullProp(handlerFacade, "handlerFacade null");
        QCheckUtil.notNull(function, "function null");

        try {
            handlerFacade.initAllToCurrentThread();

            return function.call();
        } catch (Throwable e) {
            throw QErrorUtil.sneakyError(e);
        } finally {
            handlerFacade.clearAllFromCurrentThread();
        }
    }

    /**
     * 初始然后清理
     *
     * @param function
     */
    public static void initThenClear(FunctionWithoutResult function) {
        QCheckUtil.notNullProp(handlerFacade, "handlerFacade null");
        QCheckUtil.notNull(function, "function null");

        try {
            handlerFacade.initAllToCurrentThread();

            function.call();
        } catch (Throwable e) {
            throw QErrorUtil.sneakyError(e);
        } finally {
            handlerFacade.clearAllFromCurrentThread();
        }
    }

}
