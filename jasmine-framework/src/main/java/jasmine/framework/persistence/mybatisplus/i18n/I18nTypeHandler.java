package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.core.constant.NumberConstants;
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

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String[] parts = columnName.split(",", -1);
        if (parts.length < NumberConstants.NUMBER_2) {
            return rs.getString(columnName);
        }

        String part = parts[0];
        String result = rs.getString(part);

        if (result == null) {
            part = parts[NumberConstants.NUMBER_1];
            result = rs.getString(part);
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
