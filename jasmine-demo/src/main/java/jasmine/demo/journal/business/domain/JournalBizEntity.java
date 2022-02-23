package jasmine.demo.journal.business.domain;

import jasmine.demo.journal.business.facade.JournalBizFacade;

/**
 * @author mh.z
 */
public class JournalBizEntity {
    private JournalBizFacade facade;

    public JournalBizEntity(JournalBizFacade facade) {
        this.facade = facade;
    }

}
