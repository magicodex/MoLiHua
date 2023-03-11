package jasmine.framework.mybatis.crypto;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class CryptoFieldUtilTest {
    private CryptoProvider previousProvider;

    @Before
    public void setUp() {
        previousProvider = CryptoFieldUtil.getCryptoProvider();
        CryptoFieldUtil.initUtil(null);
    }

    @After
    public void tearDown() {
        CryptoFieldUtil.initUtil(previousProvider);
    }

    @Test
    public void testEncrypt() {
        CryptoProvider provider = Mockito.mock(CryptoProvider.class);
        Mockito.when(provider.encrypt(Mockito.any()))
                .thenReturn("encryptedText");
        Mockito.when(provider.decrypt(Mockito.any()))
                .thenReturn("decryptedText");
        CryptoFieldUtil.initUtil(provider);

        String actual = CryptoFieldUtil.encrypt("decryptedText");
        Assert.assertEquals("encryptedText", actual);
    }

    @Test
    public void testDecrypt() {
        CryptoProvider provider = Mockito.mock(CryptoProvider.class);
        Mockito.when(provider.encrypt(Mockito.any()))
                .thenReturn("encryptedText");
        Mockito.when(provider.decrypt(Mockito.any()))
                .thenReturn("decryptedText");
        CryptoFieldUtil.initUtil(provider);

        String actual = CryptoFieldUtil.decrypt("encryptedText");
        Assert.assertEquals("decryptedText", actual);
    }

}
