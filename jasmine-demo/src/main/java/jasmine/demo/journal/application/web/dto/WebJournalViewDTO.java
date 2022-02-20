package jasmine.demo.journal.application.web.dto;

import jasmine.core.util.QDateUtil;
import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.business.dto.JournalDTO;

/**
 * @author mh.z
 */
public class WebJournalViewDTO {
    private Long id;
    /** 标题 */
    private String journalTitle;
    /** 内容 */
    private String journalContent;
    /** 创建日期 */
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

    /**
     * 转换成 WebJournalViewDTO 对象
     *
     * @param journalDTO
     * @return
     */
    public static WebJournalViewDTO fromJournalDTO(JournalDTO journalDTO) {
        if (journalDTO == null) {
            return null;
        }

        WebJournalViewDTO webJournalViewDTO = QMapperUtil.mapTo(journalDTO, WebJournalViewDTO.class);
        webJournalViewDTO.setCreatedDateText(QDateUtil.formatYearSecond(journalDTO.getCreatedDate()));

        return webJournalViewDTO;
    }

}
