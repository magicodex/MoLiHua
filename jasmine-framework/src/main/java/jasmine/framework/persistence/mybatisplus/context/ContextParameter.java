package jasmine.framework.persistence.mybatisplus.context;

/**
 * <p>
 * 上下文参数。
 * </p>
 *
 * @author mh.z
 */
public interface ContextParameter {

    /**
     * 返回租户ID
     *
     * @return
     */
    Long getTenantId();

    /**
     * 返回语言代码
     *
     * @return
     */
    String getLandCode();
}
