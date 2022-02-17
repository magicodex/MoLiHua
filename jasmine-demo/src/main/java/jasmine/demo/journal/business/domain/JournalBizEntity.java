package jasmine.demo.journal.business.domain;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.journal.business.domain.infrastructure.JournalInfrastructure;

/**
 * @author mh.z
 */
public class JournalBizEntity {
    private JournalInfrastructure infrastructure;

    public JournalBizEntity(RuntimeProvider provider) {
        this.infrastructure = provider.getByType(JournalInfrastructure.class);
    }

}
