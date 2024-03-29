package jasmine.framework.context.thread;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.ErrorUtil;
import jasmine.framework.common.util.function.FunctionWithResult;
import jasmine.framework.common.util.function.FunctionWithoutResult;
import jasmine.framework.context.WithContext;

/**
 * <p>
 * 提供帮助类用于初始和清理线程变量。
 * </p>
 *
 * @author mh.z
 */
public class ContextManagementUtil implements WithContext {
    private static ContextHandlerFacade handlerFacade;

    public static void initUtil(ContextHandlerFacade handlerFacade) {
        ContextManagementUtil.handlerFacade = handlerFacade;
    }

    public static ContextHandlerFacade getHandlerFacade() {
        return handlerFacade;
    }

    /**
     * 初始然后清理
     *
     * @param function
     * @param <T>
     * @return
     */
    public static <T> T manageContext(FunctionWithResult<T> function) {
        CheckUtil.notNullProp(handlerFacade, "handlerFacade null");
        CheckUtil.notNull(function, "function null");

        try {
            handlerFacade.initAllToCurrentThread();

            return function.call();
        } catch (Throwable e) {
            throw ErrorUtil.sneakyError(e);
        } finally {
            handlerFacade.clearAllFromCurrentThread();
        }
    }

    /**
     * 初始然后清理
     *
     * @param function
     */
    public static void manageContext(FunctionWithoutResult function) {
        CheckUtil.notNullProp(handlerFacade, "handlerFacade null");
        CheckUtil.notNull(function, "function null");

        try {
            handlerFacade.initAllToCurrentThread();

            function.call();
        } catch (Throwable e) {
            throw ErrorUtil.sneakyError(e);
        } finally {
            handlerFacade.clearAllFromCurrentThread();
        }
    }

}
