package jasmine.framework.database.mybatisplus.injector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;

/**
 * @author mh.z
 */
public class I18nSupportSelectPage extends AbstractI18nSupportMethod {
    private static final String METHOD_NAME = "selectPageWithI18n";

    public I18nSupportSelectPage() {
        super(METHOD_NAME, SqlMethod.SELECT_PAGE.getSql());
    }

}
