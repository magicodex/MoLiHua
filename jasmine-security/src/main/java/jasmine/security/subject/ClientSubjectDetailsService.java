package jasmine.security.subject;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * @author mh.z
 */
public interface ClientSubjectDetailsService extends ClientDetailsService {

    @Override
    ClientSubject loadClientByClientId(String clientId) throws ClientRegistrationException;
}
