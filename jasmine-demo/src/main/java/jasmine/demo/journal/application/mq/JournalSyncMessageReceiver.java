package jasmine.demo.journal.application.mq;

import jasmine.core.util.QCheckUtil;
import jasmine.demo.journal.business.dto.JournalSaveDTO;
import jasmine.demo.journal.business.dto.JournalSyncMessageDTO;
import jasmine.demo.journal.business.service.JournalService;
import jasmine.framework.remote.mq.MessageReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@RequiredArgsConstructor
@Component
public class JournalSyncMessageReceiver implements MessageReceiver<JournalSyncMessageDTO> {
    private final JournalService journalService;

    @Override
    public Class<JournalSyncMessageDTO> getType() {
        return JournalSyncMessageDTO.class;
    }

    @Override
    public void receive(JournalSyncMessageDTO messageDTO) {
        QCheckUtil.notNull(messageDTO, "messageDTO null");

        JournalSaveDTO journalSaveDTO = JournalSaveDTO.fromJournalSyncMessageDTO(messageDTO);
        journalService.saveJournal(journalSaveDTO);
    }

}
