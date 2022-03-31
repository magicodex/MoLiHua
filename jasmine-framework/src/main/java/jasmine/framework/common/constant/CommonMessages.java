package jasmine.framework.common.constant;

import jasmine.core.i18n.DeclareI18N;

/**
 * @author mh.z
 */
public interface CommonMessages {

    @DeclareI18N("更新出错")
    String UPDATE_ROW_COUNT_MISMATCH = "$jasmine.mybatisPlus.updateRowCountMismatch";

    @DeclareI18N("删除出错")
    String DELETE_ROW_COUNT_MISMATCH = "$jasmine.mybatisPlus.deleteRowCountMismatch";
}
