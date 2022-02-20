package jasmine.demo.journal.persistence.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.demo.journal.persistence.entity.JournalEO;
import jasmine.demo.journal.persistence.mapper.JournalMapper;
import jasmine.demo.journal.persistence.dto.JournalQueryDbDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mh.z
 */
@Repository
public class JournalDao {
    private JournalMapper baseMapper;

    public JournalDao(JournalMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查询日记
     *
     * @param param
     * @param page
     * @return
     */
    public List<JournalEO> pageJournalsByCond(JournalQueryDbDTO param, Page<JournalEO> page) {
        QCheckUtil.notNull(param, "param null");
        QCheckUtil.notNull(page, "page null");

        LambdaQueryWrapper<JournalEO> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(JournalEO::getUserId, param.getUserId());
        wrapper.orderByDesc(JournalEO::getCreatedDate);

        Page<JournalEO> resultPage = baseMapper.selectPage(page, wrapper);
        return resultPage.getRecords();
    }

    /**
     * 保存日记
     *
     * @param journal
     */
    public void saveJournal(JournalEO journal) {
        QCheckUtil.notNull(journal, "journal null");

        journal.setTenantId(CurrentSubject.getTenantId());
        baseMapper.insert(journal);
    }

}
