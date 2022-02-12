package jasmine.demo.system.remote.rabbit;

import jasmine.framework.remote.mq.ConsumeMessageContext;
import jasmine.framework.remote.mq.ConsumeMessageProvider;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class Example2ConsumeMessageProvider implements ConsumeMessageProvider {

    @Override
    public void consume(ConsumeMessageContext context, Object data) {
        // TODO
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }

}
