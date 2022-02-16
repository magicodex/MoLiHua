package jasmine.demo.journal.business.domain;

import jasmine.demo.journal.business.domain.dependency.JournalDependency;

/**
 * @author mh.z
 */
public class JournalDO {
    private JournalDependency dependency;

    public JournalDO(JournalDependency dependency) {
        this.dependency = dependency;
    }

}
