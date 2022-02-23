package jasmine.demo.journal.application.dto;

import jasmine.core.util.QDateUtil;
import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.business.dto.JournalDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mh.z
 */
@NoArgsConstructor
@Data
public class WebJournalViewDTO {
    private Long id;
    /** 标题 */
    private String journalTitle;
    /** 内容 */
    private String journalContent;
    /** 创建日期 */
    private String createdDateText;

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
