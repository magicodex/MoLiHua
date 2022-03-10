package jasmine.framework.common.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * <p>
 * 唯一主键生成工具类。
 * </p>
 *
 * @author mh.z
 */
public class UniqueKeyUtil {

    /**
     * 返回下一个 Long 值
     *
     * @return
     */
    public static Long nextLong() {
        return IdWorker.getId();
    }

}
