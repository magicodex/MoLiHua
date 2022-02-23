package jasmine.demo.journal.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mh.z
 */
@NoArgsConstructor
@Data
public class JournalSyncMessageDTO {
    /** 用户ID */
    private String userId;
    /** 标题 */
    private String journalTitle;
    /** 内容 */
    private String journalContent;

}
