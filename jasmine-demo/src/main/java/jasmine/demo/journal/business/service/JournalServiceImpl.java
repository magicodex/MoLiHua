package jasmine.demo.journal.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.demo.journal.business.dto.JournalDTO;
import jasmine.demo.journal.business.dto.JournalNoticeMessageDTO;
import jasmine.demo.journal.business.dto.JournalSaveDTO;
import jasmine.demo.journal.constant.JournalLocks;
import jasmine.demo.journal.persistence.dao.JournalDao;
import jasmine.demo.journal.persistence.entity.JournalEO;
import jasmine.demo.journal.persistence.dto.JournalQueryDbDTO;
import jasmine.framework.lock.annotation.DistributedLock;
import jasmine.framework.remote.mq.SendMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mh.z
 */
@Service
public class JournalServiceImpl implements JournalService {
    private JournalDao journalDao;
    private SendMessageService sendMessageService;

    public JournalServiceImpl(JournalDao journalDao, SendMessageService sendMessageService) {
        this.journalDao = journalDao;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public List<JournalDTO> pageAllJournals(Page page) {
        QCheckUtil.notNull(page, "page null");
        JournalQueryDbDTO param = new JournalQueryDbDTO();
        param.setUserId(CurrentSubject.getUserId());

        List<JournalEO> journalEOList = journalDao.pageJournalsByCond(param, page);
        List<JournalDTO> journalDTOList = QCollectionUtil.mapToList(journalEOList,
                JournalDTO::fromJournalEO);

        return journalDTOList;
    }

    /**
     * 此处使用分布式锁没有业务上的意义，只是用来测试分布式锁是否生效。
     */
    @DistributedLock(category = JournalLocks.JOURNAL_SAVE, key = "#journal.journalTitle")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JournalDTO saveJournal(JournalSaveDTO journal) {
        QCheckUtil.notNull(journal, "journal null");

        JournalEO journalEO = new JournalEO();
        journalEO.setJournalTitle(journal.getJournalTitle());
        journalEO.setJournalContent(journal.getJournalContent());
        journalEO.setUserId(CurrentSubject.getUserId());
        journalDao.saveJournal(journalEO);

        JournalNoticeMessageDTO messageDTO = JournalNoticeMessageDTO.toJournalNoticeMessageDTO(journalEO);
        // 发送消息
        sendMessageService.send("journalNotice", messageDTO);

        return JournalDTO.fromJournalEO(journalEO);
    }

}
