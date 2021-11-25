package jasmine.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * <p>
 * 用于生成唯一标识。
 * </p>
 *
 * @author mh.z
 */
public class QUniqueKeyUtil {
    /** 雪花算法 */
    private static final Snowflake SNOWFLAKE;

    static {
        SNOWFLAKE = IdUtil.getSnowflake(1, 1);
    }

    /**
     * 生成一个新的 Long 值
     *
     * @return
     */
    public static long nextLong() {
        return SNOWFLAKE.nextId();
    }

}
