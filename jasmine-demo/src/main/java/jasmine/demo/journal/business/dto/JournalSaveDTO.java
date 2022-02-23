package jasmine.demo.journal.business.dto;

import jasmine.core.util.QMapperUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mh.z
 */
@NoArgsConstructor
@Data
public class JournalSaveDTO {
    /** 标题 */
    private String journalTitle;
    /** 内容 */
    private String journalContent;

    /**
     * 转换成 JournalSaveDTO 对象
     *
     * @param messageDTO
     * @return
     */
    public static JournalSaveDTO fromJournalSyncMessageDTO(JournalSyncMessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }

        return QMapperUtil.mapTo(messageDTO, JournalSaveDTO.class);
    }

}
