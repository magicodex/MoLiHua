package jasmine.demo.system.remote.rabbit;

import jasmine.core.util.QCheckUtil;
import jasmine.demo.journal.business.adapter.JournalDtoAdapter;
import jasmine.demo.journal.business.dto.JournalSaveDTO;
import jasmine.demo.journal.business.dto.JournalSyncMessageDTO;
import jasmine.demo.journal.business.service.JournalService;
import jasmine.framework.remote.mq.ConsumeMessageContext;
import jasmine.framework.remote.mq.ConsumeMessageProvider;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class JournalSyncConsumeMessageProvider implements ConsumeMessageProvider {
    private JournalService journalService;

    public JournalSyncConsumeMessageProvider(JournalService journalService) {
        this.journalService = journalService;
    }

    @Override
    public void consume(ConsumeMessageContext context, Object data) {
        QCheckUtil.notNull(context, "context null");
        QCheckUtil.notNull(data, "data null");
        JournalSyncMessageDTO messageDTO = (JournalSyncMessageDTO) data;

        JournalSaveDTO journalSaveDTO = JournalDtoAdapter.toJournalSaveDTO(messageDTO);
        journalService.saveJournal(journalSaveDTO);
    }

    @Override
    public Class<?> getType() {
        return JournalSyncMessageDTO.class;
    }

}
