package jasmine.framework.common.constant;

import jasmine.framework.i18n.DeclareI18N;

/**
 * @author mh.z
 */
public interface CommonMessageConstants {

    @DeclareI18N("未找到数据 {0}[{1}={2}]！")
    String NOT_FOUND_THE_DATA = "$jasmine.common.notFoundTheData";

    @DeclareI18N("数据 {0}[{1}={2}] 无效！")
    String INVALID_DATA = "$jasmine.common.invalidData";
}
