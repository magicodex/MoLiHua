package jasmine.framework.persistence.mybatisplus.crypto;

import jasmine.core.util.CheckUtil;
import jasmine.framework.common.security.CryptoProvider;

/**
 * @author mh.z
 */
public class CryptoFieldUtil {
    private static CryptoProvider cryptoProvider;

    public static void initUtil(CryptoProvider cryptoProvider) {
        CryptoFieldUtil.cryptoProvider = cryptoProvider;
    }

    public static CryptoProvider getCryptoProvider() {
        return cryptoProvider;
    }

    /**
     * 加密
     *
     * @param source
     * @return
     */
    public static String encrypt(String source) {
        CheckUtil.notNullProp(cryptoProvider, "cryptoProvider null");

        return cryptoProvider.encrypt(source);
    }

    /**
     * 解密
     *
     * @param source
     * @return
     */
    public static String decrypt(String source) {
        CheckUtil.notNullProp(cryptoProvider, "cryptoProvider null");

        return cryptoProvider.decrypt(source);
    }

}
