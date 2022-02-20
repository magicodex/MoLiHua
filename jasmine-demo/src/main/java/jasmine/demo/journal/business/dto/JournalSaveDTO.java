package jasmine.demo.journal.business.dto;

import jasmine.core.util.QMapperUtil;

/**
 * @author mh.z
 */
public class JournalSaveDTO {
    /** 标题 */
    private String journalTitle;
    /** 内容 */
    private String journalContent;

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalContent() {
        return journalContent;
    }

    public void setJournalContent(String journalContent) {
        this.journalContent = journalContent;
    }

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
