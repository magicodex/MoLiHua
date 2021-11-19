package jasmine.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class QUniqueKeyUtil {
    private static final Snowflake SNOWFLAKE;

    static {
        SNOWFLAKE = IdUtil.getSnowflake(1, 1);
    }

    public static long nextLong() {
        return SNOWFLAKE.nextId();
    }

}
