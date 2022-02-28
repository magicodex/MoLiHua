package jasmine.framework.context;

import jasmine.core.context.handler.ContextHandlerFacade;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.common.function.FunctionWithResult;
import jasmine.framework.common.function.FunctionWithoutResult;

/**
 * @author mh.z
 */
public class ContextInitAndClearHelper {
    private static ContextHandlerFacade handlerFacade;

    public static void initUtil(ContextHandlerFacade handlerFacade) {
        ContextInitAndClearHelper.handlerFacade = handlerFacade;
    }

    /**
     * 初始然后清理
     *
     * @param function
     * @param <T>
     * @return
     */
    public <T> T initThenClear(FunctionWithResult<T> function) {
        QCheckUtil.notNullProp(handlerFacade, "handlerFacade null");
        QCheckUtil.notNull(function, "function null");

        try {
            handlerFacade.initAll();

            return function.call();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            handlerFacade.clearAll();
        }
    }

    /**
     * 初始然后清理
     *
     * @param function
     */
    public void initThenClear(FunctionWithoutResult function) {
        QCheckUtil.notNullProp(handlerFacade, "handlerFacade null");
        QCheckUtil.notNull(function, "function null");

        try {
            handlerFacade.initAll();

            function.call();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            handlerFacade.clearAll();
        }
    }

}
