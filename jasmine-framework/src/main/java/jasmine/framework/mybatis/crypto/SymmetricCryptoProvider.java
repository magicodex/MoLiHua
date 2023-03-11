package jasmine.framework.mybatis.crypto;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import jasmine.core.util.StringUtil;
import jasmine.framework.common.security.CryptoProvider;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author mh.z
 */
public class SymmetricCryptoProvider implements CryptoProvider {
    private byte[] keyBytes;
    private byte[] ivBytes;

    /** Base64编码器 */
    private static final Base64.Encoder BASE64_ENCODER;
    /** Base64解码器 */
    private static final Base64.Decoder BASE64_DECODER;

    static {
        BASE64_ENCODER = Base64.getEncoder();
        BASE64_DECODER = Base64.getDecoder();
    }

    public SymmetricCryptoProvider(String secret, String salt) {
        keyBytes = DigestUtil.sha256(secret);

        if (StringUtil.isNotEmpty(salt)) {
            ivBytes = DigestUtil.md5(salt);
        } else {
            ivBytes = DigestUtil.md5(secret);
        }
    }

    @Override
    public String encrypt(String source) {
        if (StringUtil.isEmpty(source)) {
            return source;
        }

        // 转换文本成字节（明文数据）
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        // 对数据做加密处理
        SymmetricCrypto crypto = getSymmetricCrypto();
        byte[] encryptedBytes = crypto.encrypt(sourceBytes);
        // 把字节（加密数据）编码成base64文本
        byte[] base64Bytes = BASE64_ENCODER.encode(encryptedBytes);
        String base64Text = new String(base64Bytes, StandardCharsets.UTF_8);

        return base64Text;
    }

    @Override
    public String decrypt(String source) {
        if (StringUtil.isEmpty(source)) {
            return source;
        }

        // 把base64文本解码成字节（加密数据）
        byte[] base64Bytes = source.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = BASE64_DECODER.decode(base64Bytes);
        // 对数据做解密处理
        SymmetricCrypto crypto = getSymmetricCrypto();
        byte[] decryptedBytes = crypto.decrypt(encryptedBytes);
        // 转换字节（明文数据）成文本
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

        return decryptedText;
    }

    /**
     * 返回对称加密
     *
     * @return
     */
    protected SymmetricCrypto getSymmetricCrypto() {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, keyBytes, ivBytes);

        return aes;
    }

}
