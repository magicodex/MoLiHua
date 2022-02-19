package jasmine.demo.journal.business.dto;

/**
 * @author mh.z
 */
public class JournalNoticeMessageDTO {
    /** 用户ID */
    private Long userId;
    /** 标题 */
    private String journalTitle;
    /** 内容 */
    private String journalContent;
    /** 创建日期 */
    private String createdDateText;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getCreatedDateText() {
        return createdDateText;
    }

    public void setCreatedDateText(String createdDateText) {
        this.createdDateText = createdDateText;
    }
    
}
