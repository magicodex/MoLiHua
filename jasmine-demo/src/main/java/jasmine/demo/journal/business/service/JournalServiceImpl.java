package jasmine.demo.journal.business.service;

import jasmine.demo.journal.persistence.dao.JournalDao;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class JournalServiceImpl implements JournalService {
    private JournalDao journalDao;

    public JournalServiceImpl(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

}
