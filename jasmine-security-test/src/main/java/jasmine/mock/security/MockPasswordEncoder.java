package jasmine.mock.security;

import jasmine.framework.common.util.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mh.z
 */
public class MockPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return StringUtil.toString(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StringUtil.equals(rawPassword, encodedPassword);
    }

}
