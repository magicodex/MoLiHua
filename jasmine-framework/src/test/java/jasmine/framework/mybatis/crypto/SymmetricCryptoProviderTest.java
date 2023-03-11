package jasmine.framework.mybatis.crypto;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class SymmetricCryptoProviderTest {

    @Test
    public void test() {
        SymmetricCryptoProvider provider = new SymmetricCryptoProvider("secret1", "salt1");
        String encryptedContent = provider.encrypt("content1");
        String decryptedContent = provider.decrypt(encryptedContent);

        Assert.assertEquals("content1", decryptedContent);
    }

}
