package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.core.exception.ApplicationException;
import jasmine.framework.common.constant.CommonMessages;

/**
 * @author mh.z
 */
public class BaseMapperHelper {

    /**
     * 更新记录并检查影响的行数
     *
     * @param baseMapper
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> int strictUpdateById(BaseMapper<T> baseMapper, T entity) {
        int rowCount = baseMapper.updateById(entity);

        if (rowCount == 0) {
            throw new ApplicationException(CommonMessages.UPDATE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }

}
