package jasmine.demo.journal.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.business.dto.JournalSaveDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author mh.z
 */
@NoArgsConstructor
@Data
@ApiModel(description = "保存日记")
public class WebJournalSaveDTO {

    @Length(max = 20)
    @ApiModelProperty("标题")
    private String journalTitle;

    @Length(max = 50)
    @ApiModelProperty("内容")
    private String journalContent;

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
