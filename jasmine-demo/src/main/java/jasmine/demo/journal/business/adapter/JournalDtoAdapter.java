package jasmine.demo.journal.business.adapter;

import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.business.dto.JournalDTO;
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

        JournalDTO journalDTO = QMapperUtil.mapTo(journalEO, JournalDTO.class);
        return journalDTO;
    }

}
