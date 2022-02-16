package jasmine.framework.persistence.mybatisplus;

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
}
