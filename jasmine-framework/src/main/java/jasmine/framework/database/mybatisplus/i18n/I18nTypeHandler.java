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
    private static final String COLUMN_NAME_I18N_SUFFIX = "_i18n";

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String columnNameI18n = columnName + COLUMN_NAME_I18N_SUFFIX;

        String result = rs.getString(columnNameI18n);

        if (result == null) {
            result = rs.getString(columnName);
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
