package jasmine.demo.journal.business.dto;

import jasmine.core.util.QDateUtil;
import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.persistence.entity.JournalEO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mh.z
 */
@NoArgsConstructor
@Data
public class JournalNoticeMessageDTO {
    /** 用户ID */
    private Long userId;
    /** 标题 */
    private String journalTitle;
    /** 内容 */
    private String journalContent;
    /** 创建日期 */
    private String createdDateText;

    /**
     * 转换成 JournalNoticeMessageDTO 对象
     *
     * @param journalEO
     * @return
     */
    public static JournalNoticeMessageDTO fromJournalEO(JournalEO journalEO) {
        if (journalEO == null) {
            return null;
        }

        JournalNoticeMessageDTO messageDTO = QMapperUtil.mapTo(journalEO,
                JournalNoticeMessageDTO.class);
        messageDTO.setCreatedDateText(QDateUtil.formatYearSecond(journalEO.getCreatedDate()));

        return messageDTO;
    }

}
