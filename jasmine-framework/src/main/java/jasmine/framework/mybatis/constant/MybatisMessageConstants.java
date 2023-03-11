package jasmine.framework.mybatis.constant;

import jasmine.core.i18n.DeclareI18N;

/**
 * @author mh.z
 */
public interface MybatisMessageConstants {

    @DeclareI18N("更新出错")
    String UPDATE_ROW_COUNT_MISMATCH = "$jasmine.mybatisPlus.updateRowCountMismatch";

    @DeclareI18N("删除出错")
    String DELETE_ROW_COUNT_MISMATCH = "$jasmine.mybatisPlus.deleteRowCountMismatch";

    @DeclareI18N("查询出错")
    String SELECT_ROW_COUNT_MISMATCH = "$jasmine.mybatisPlus.selectRowCountMismatch";
}
