package jasmine.mybatis.crypto;

/**
 * @author mh.z
 */
public interface CryptoProvider {

    /**
     * 加密
     *
     * @param source
     * @return
     */
    String encrypt(String source);

    /**
     * 解密
     *
     * @param source
     * @return
     */
    String decrypt(String source);
}
