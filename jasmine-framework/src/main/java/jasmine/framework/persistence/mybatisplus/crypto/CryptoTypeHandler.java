package jasmine.framework.persistence.mybatisplus.crypto;

import jasmine.core.util.QCheckUtil;
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
    private static CryptoProvider cryptoProvider;

    public static void setCryptoProvider(CryptoProvider cryptoProvider) {
        CryptoTypeHandler.cryptoProvider = cryptoProvider;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        QCheckUtil.notNullProp(cryptoProvider, "cryptoProvider null");

        if (parameter != null) {
            String encryptedText = cryptoProvider.encrypt(parameter);
            ps.setString(i, encryptedText);
        } else {
            ps.setNull(i, Types.VARCHAR);
        }
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        QCheckUtil.notNullProp(cryptoProvider, "cryptoProvider null");

        String result = rs.getString(columnName);
        if (result == null) {
            return null;
        }

        String decryptedText = cryptoProvider.decrypt(result);
        return decryptedText;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        QCheckUtil.notNullProp(cryptoProvider, "cryptoProvider null");

        String result = rs.getString(columnIndex);
        if (result == null) {
            return null;
        }

        String decryptedText = cryptoProvider.decrypt(result);
        return decryptedText;
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        QCheckUtil.notNullProp(cryptoProvider, "cryptoProvider null");

        String result = cs.getString(columnIndex);
        if (result == null) {
            return null;
        }

        String decryptedText = cryptoProvider.decrypt(result);
        return decryptedText;
    }

}
