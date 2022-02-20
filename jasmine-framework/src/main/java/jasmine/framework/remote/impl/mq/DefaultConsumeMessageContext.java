package jasmine.framework.remote.impl.mq;

import jasmine.framework.remote.mq.ConsumeMessageContext;

/**
 * @author mh.z
 */
public class DefaultConsumeMessageContext implements ConsumeMessageContext {
    /** 类别 */
    private String category;

    public DefaultConsumeMessageContext(String category) {
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }

}
