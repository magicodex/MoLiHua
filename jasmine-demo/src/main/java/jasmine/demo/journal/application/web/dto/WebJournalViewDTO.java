package jasmine.demo.journal.application.web.dto;

/**
 * @author mh.z
 */
public class WebJournalViewDTO {
    private Long id;
    private String journalTitle;
    private String journalContent;

    private String createdDateText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
