package jasmine.framework.mock.common;

import jasmine.framework.common.security.CryptoProvider;

/**
 * @author mh.z
 */
public class MockCryptoProvider implements CryptoProvider {

    @Override
    public String encrypt(String source) {
        return source;
    }

    @Override
    public String decrypt(String source) {
        return source;
    }

}
