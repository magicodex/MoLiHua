package jasmine.demo.journal.business.domain;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.journal.business.facade.JournalBizFacade;

/**
 * @author mh.z
 */
public class JournalBizEntity {
    private JournalBizFacade infrastructure;

    public JournalBizEntity(RuntimeProvider provider) {
        this.infrastructure = provider.getByType(JournalBizFacade.class);
    }

}
