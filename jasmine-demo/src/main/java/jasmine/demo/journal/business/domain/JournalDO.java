package jasmine.demo.journal.business.domain;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.journal.business.domain.infrastructure.JournalInfrastructure;

/**
 * @author mh.z
 */
public class JournalDO {
    private JournalInfrastructure infrastructure;

    public JournalDO(RuntimeProvider provider) {
        this.infrastructure = provider.getByType(JournalInfrastructure.class);
    }

}
