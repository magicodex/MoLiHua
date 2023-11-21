package jasmine.framework.database.mybatisplus.i18n;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mh.z
 */
public class I18nTypeHandler implements TypeHandler<String> {
    private static final String I18N_COLUMN_NAME_SUFFIX = "_i18n";
    private static final String LANG_COLUMN_NAME_SUFFIX = "_lang";

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String langColumnName = columnName + LANG_COLUMN_NAME_SUFFIX;
        String result = rs.getString(langColumnName);

        if (result == null) {
            result = rs.getString(columnName);
        } else {
            String i18nColumnName = columnName + I18N_COLUMN_NAME_SUFFIX;
            result = rs.getString(i18nColumnName);
        }

        return result;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

}
