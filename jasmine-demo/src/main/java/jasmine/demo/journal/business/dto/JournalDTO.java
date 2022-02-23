package jasmine.demo.journal.business.dto;

import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.persistence.entity.JournalEO;
import jasmine.framework.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mh.z
 */
@NoArgsConstructor
@Data
public class JournalDTO extends BaseEntity {
    /** 标题 */
    private String journalTitle;
    /** 内容 */
    private String journalContent;

    /**
     * 转换成 JournalDTO 对象
     *
     * @param journalEO
     * @return
     */
    public static JournalDTO fromJournalEO(JournalEO journalEO) {
        if (journalEO == null) {
            return null;
        }

        return QMapperUtil.mapTo(journalEO, JournalDTO.class);
    }

}
