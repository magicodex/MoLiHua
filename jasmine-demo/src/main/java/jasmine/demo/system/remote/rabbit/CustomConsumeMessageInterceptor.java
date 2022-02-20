package jasmine.demo.system.remote.rabbit;

import jasmine.framework.remote.mq.ConsumeMessageContext;
import jasmine.framework.remote.mq.ConsumeMessageInterceptor;
import jasmine.framework.remote.mq.ConsumeMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class CustomConsumeMessageInterceptor implements ConsumeMessageInterceptor {
    private static Logger logger = LoggerFactory.getLogger(CustomConsumeMessageInterceptor.class);

    @Override
    public void intercept(ConsumeMessageProvider provider, ConsumeMessageContext context, Object data) {
        // TODO 目前消费出错只是记录日志，实际业务场景中要做相应错误处理
        try {
            provider.consume(context, data);
        } catch (Exception e) {
            logger.error("consume failed", e);
        }
    }

}
