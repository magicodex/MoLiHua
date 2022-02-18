package jasmine.demo.journal.business.dto;

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

}
