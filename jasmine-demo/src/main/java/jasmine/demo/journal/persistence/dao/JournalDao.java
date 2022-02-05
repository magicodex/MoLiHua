package jasmine.demo.journal.persistence.dao;

import jasmine.demo.journal.persistence.mapper.JournalMapper;
import org.springframework.stereotype.Repository;

/**
 * @author mh.z
 */
@Repository
public class JournalDao {
    private JournalMapper baseMapper;

    public JournalDao(JournalMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

}
