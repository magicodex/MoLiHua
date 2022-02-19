package jasmine.demo.journal.business.adapter;

import jasmine.core.util.QDateUtil;
import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.business.dto.JournalDTO;
import jasmine.demo.journal.business.dto.JournalNoticeMessageDTO;
import jasmine.demo.journal.business.dto.JournalSaveDTO;
import jasmine.demo.journal.business.dto.JournalSyncMessageDTO;
import jasmine.demo.journal.persistence.entity.JournalEO;

/**
 * @author mh.z
 */
public class JournalDtoAdapter {

    /**
     * 转换成 JournalDTO 对象
     *
     * @param journalEO
     * @return
     */
    public static JournalDTO toJournalDTO(JournalEO journalEO) {
        if (journalEO == null) {
            return null;
        }

        return QMapperUtil.mapTo(journalEO, JournalDTO.class);
    }

    /**
     * 转换成 JournalNoticeMessageDTO 对象
     *
     * @param journalEO
     * @return
     */
    public static JournalNoticeMessageDTO toJournalNoticeMessageDTO(JournalEO journalEO) {
        if (journalEO == null) {
            return null;
        }

        JournalNoticeMessageDTO messageDTO = QMapperUtil.mapTo(journalEO,
                JournalNoticeMessageDTO.class);
        messageDTO.setCreatedDateText(QDateUtil.formatYearSecond(journalEO.getCreatedDate()));

        return messageDTO;
    }

    /**
     * 转换成 JournalSaveDTO 对象
     *
     * @param messageDTO
     * @return
     */
    public static JournalSaveDTO toJournalSaveDTO(JournalSyncMessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }

        return QMapperUtil.mapTo(messageDTO, JournalSaveDTO.class);
    }

}
