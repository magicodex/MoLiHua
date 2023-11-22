package jasmine.framework.database.mybatisplus.injector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;

/**
 * @author mh.z
 */
public class I18nSupportSelectList extends AbstractI18nSupportMethod {
    private static final String METHOD_NAME = "selectListWithI18n";

    public I18nSupportSelectList() {
        super(METHOD_NAME, SqlMethod.SELECT_LIST.getSql());
    }

}
