package jasmine.demo.journal.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.demo.journal.business.adapter.JournalDtoAdapter;
import jasmine.demo.journal.business.dto.JournalDTO;
import jasmine.demo.journal.business.dto.JournalSaveDTO;
import jasmine.demo.journal.persistence.dao.JournalDao;
import jasmine.demo.journal.persistence.entity.JournalEO;
import jasmine.demo.journal.persistence.param.JournalByCondParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mh.z
 */
@Service
public class JournalServiceImpl implements JournalService {
    private JournalDao journalDao;

    public JournalServiceImpl(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    @Override
    public List<JournalDTO> pageAllJournals(Page page) {
        QCheckUtil.notNull(page, "page null");
        JournalByCondParam param = new JournalByCondParam();
        param.setUserId(CurrentSubject.getUserId());

        List<JournalEO> journalEOList = journalDao.pageJournalsByCond(param, page);
        List<JournalDTO> journalDTOList = QCollectionUtil.mapToList(journalEOList,
                JournalDtoAdapter::toJournalDTO);

        return journalDTOList;
    }

    @Override
    public void saveJournal(JournalSaveDTO journal) {
        QCheckUtil.notNull(journal, "journal null");

        JournalEO journalEO = new JournalEO();
        journalEO.setJournalTitle(journal.getJournalTitle());
        journalEO.setJournalContent(journal.getJournalContent());
        journalEO.setUserId(CurrentSubject.getUserId());
        journalEO.setTenantId(CurrentSubject.getTenantId());

        journalDao.saveJournal(journalEO);
    }

}
