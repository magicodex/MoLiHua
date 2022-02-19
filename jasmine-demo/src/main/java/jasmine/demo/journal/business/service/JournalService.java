package jasmine.demo.journal.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jasmine.demo.journal.business.dto.JournalDTO;
import jasmine.demo.journal.business.dto.JournalSaveDTO;

import java.util.List;

/**
 * @author mh.z
 */
public interface JournalService {

    /**
     * 查询日记
     *
     * @param page
     * @return
     */
    List<JournalDTO> pageAllJournals(Page page);

    /**
     * 保存日记
     *
     * @param journal
     */
    JournalDTO saveJournal(JournalSaveDTO journal);
}
