package jasmine.framework.persistence.mybatisplus.crypto;

import jasmine.framework.common.security.CryptoProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class CryptoFieldHelperTest {
    private CryptoProvider previousProvider;

    @Before
    public void setUp() {
        previousProvider = CryptoFieldHelper.getCryptoProvider();
        CryptoFieldHelper.initUtil(null);
    }

    @After
    public void tearDown() {
        CryptoFieldHelper.initUtil(previousProvider);
    }

    @Test
    public void testEncrypt() {
        CryptoProvider provider = Mockito.mock(CryptoProvider.class);
        Mockito.when(provider.encrypt(Mockito.any()))
                .thenReturn("encryptedText");
        Mockito.when(provider.decrypt(Mockito.any()))
                .thenReturn("decryptedText");
        CryptoFieldHelper.initUtil(provider);

        String actual = CryptoFieldHelper.encrypt("decryptedText");
        Assert.assertEquals("encryptedText", actual);
    }

    @Test
    public void testDecrypt() {
        CryptoProvider provider = Mockito.mock(CryptoProvider.class);
        Mockito.when(provider.encrypt(Mockito.any()))
                .thenReturn("encryptedText");
        Mockito.when(provider.decrypt(Mockito.any()))
                .thenReturn("decryptedText");
        CryptoFieldHelper.initUtil(provider);

        String actual = CryptoFieldHelper.decrypt("encryptedText");
        Assert.assertEquals("decryptedText", actual);
    }

}
