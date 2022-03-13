package jasmine.framework.persistence.mybatisplus.crypto;

import jasmine.core.util.QCheckUtil;
import jasmine.framework.common.crypto.CryptoProvider;

/**
 * @author mh.z
 */
public class CryptoFieldHelper {
    private static CryptoProvider cryptoProvider;

    public static void initUtil(CryptoProvider cryptoProvider) {
        CryptoFieldHelper.cryptoProvider = cryptoProvider;
    }

    /**
     * 加密
     *
     * @param source
     * @return
     */
    public static String encrypt(String source) {
        QCheckUtil.notNullProp(cryptoProvider, "cryptoProvider null");

        return cryptoProvider.encrypt(source);
    }

    /**
     * 解密
     *
     * @param source
     * @return
     */
    public static String decrypt(String source) {
        QCheckUtil.notNullProp(cryptoProvider, "cryptoProvider null");

        return cryptoProvider.decrypt(source);
    }

}
