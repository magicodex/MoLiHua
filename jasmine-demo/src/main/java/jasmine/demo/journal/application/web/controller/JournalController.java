package jasmine.demo.journal.application.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jasmine.core.util.QCollectionUtil;
import jasmine.demo.journal.application.web.adapter.WebJournalDtoAdapter;
import jasmine.demo.journal.application.web.dto.WebJournalSaveDTO;
import jasmine.demo.journal.application.web.dto.WebJournalViewDTO;
import jasmine.demo.journal.business.dto.JournalDTO;
import jasmine.demo.journal.business.dto.JournalSaveDTO;
import jasmine.demo.journal.business.service.JournalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
@Api(tags = "日记")
@RestController
public class JournalController {
    private JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @ApiOperation(value = "查询日记")
    @GetMapping("/journal/search")
    public ModelAndView search(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Page queryPage = new Page(page, 10);
        // 查询日记
        List<JournalDTO> recordList = journalService.pageAllJournals(queryPage);
        List<WebJournalViewDTO> webJournalViewDTOList = QCollectionUtil.mapToList(recordList,
                WebJournalDtoAdapter::toWebJournalViewDTO);

        Map<String, Object> model = Map.of("records", webJournalViewDTOList,
                "showNoData", QCollectionUtil.isEmpty(recordList),
                "showPrevPage", page > 1,
                "currPage", page,
                "prevPage", page - 1,
                "nextPage", page + 1);

        return new ModelAndView("journal/journal-search.html", model);
    }

    @ApiOperation(value = "编辑日记")
    @GetMapping("/journal/edit")
    public ModelAndView edit() {
        Map<String, Object> model = Map.of("record", new WebJournalViewDTO());

        return new ModelAndView("journal/journal-edit.html", model);
    }

    @ApiOperation(value = "保存日记")
    @PostMapping("/journal/save")
    public ModelAndView save(@ModelAttribute WebJournalSaveDTO param) {
        JournalSaveDTO journalSaveDTO = WebJournalDtoAdapter.toJournalSaveDTO(param);
        // 保存日记
        journalService.saveJournal(journalSaveDTO);

        Map<String, Object> model = Map.of("record", new WebJournalViewDTO(),
                "showSaveSuccess", true);

        return new ModelAndView("journal/journal-edit.html", model);
    }

}
