package jasmine.demo.journal.application.web.adapter;

import jasmine.core.util.QDateUtil;
import jasmine.core.util.QMapperUtil;
import jasmine.demo.journal.application.web.dto.WebJournalSaveDTO;
import jasmine.demo.journal.application.web.dto.WebJournalViewDTO;
import jasmine.demo.journal.business.dto.JournalDTO;
import jasmine.demo.journal.business.dto.JournalSaveDTO;

/**
 * @author mh.z
 */
public class WebJournalDtoAdapter {

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

        JournalSaveDTO journalSaveDTO = QMapperUtil.mapTo(webJournalSaveDTO, JournalSaveDTO.class);
        return journalSaveDTO;
    }

    /**
     * 转换成 WebJournalViewDTO 对象
     *
     * @param journalDTO
     * @return
     */
    public static WebJournalViewDTO toWebJournalViewDTO(JournalDTO journalDTO) {
        if (journalDTO == null) {
            return null;
        }

        WebJournalViewDTO webJournalViewDTO = QMapperUtil.mapTo(journalDTO, WebJournalViewDTO.class);
        webJournalViewDTO.setCreatedDateText(QDateUtil.formatYearDay(journalDTO.getCreatedDate()));

        return webJournalViewDTO;
    }

}
