package jasmine.framework.mybatis.crypto;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author mh.z
 */
public class CryptoTypeHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            String encryptedText = CryptoFieldUtil.encrypt(parameter);
            ps.setString(i, encryptedText);
        } else {
            ps.setNull(i, Types.VARCHAR);
        }
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        if (result == null) {
            return null;
        }

        String decryptedText = CryptoFieldUtil.decrypt(result);
        return decryptedText;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        if (result == null) {
            return null;
        }

        String decryptedText = CryptoFieldUtil.decrypt(result);
        return decryptedText;
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if (result == null) {
            return null;
        }

        String decryptedText = CryptoFieldUtil.decrypt(result);
        return decryptedText;
    }

}
