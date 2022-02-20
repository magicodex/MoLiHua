package jasmine.demo.journal.application.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.business.dto.JournalSaveDTO;

/**
 * @author mh.z
 */
@ApiModel(description = "保存日记")
public class WebJournalSaveDTO {

    @ApiModelProperty("标题")
    private String journalTitle;

    @ApiModelProperty("内容")
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
     * @param webJournalSaveDTO
     * @return
     */
    public static JournalSaveDTO toJournalSaveDTO(WebJournalSaveDTO webJournalSaveDTO) {
        if (webJournalSaveDTO == null) {
            return null;
        }

        return QMapperUtil.mapTo(webJournalSaveDTO, JournalSaveDTO.class);
    }

}
