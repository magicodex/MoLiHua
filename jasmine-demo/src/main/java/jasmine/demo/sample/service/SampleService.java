package jasmine.demo.sample.service;

import jasmine.core.exception.DataNotFoundException;
import jasmine.core.util.QErrorUtil;
import jasmine.core.util.QMapperUtil;
import jasmine.demo.sample.dto.SampleCreateDTO;
import jasmine.demo.sample.dto.SampleDTO;
import jasmine.demo.sample.dto.SampleUpdateDTO;
import jasmine.demo.sample.entity.Sample;
import jasmine.demo.sample.mapper.SampleMapper;
import jasmine.framework.lock.annotation.DistributedLock;
import jasmine.framework.persistence.mybatisplus.util.BaseMapperHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mh.z
 */
@Service
public class SampleService {
    private SampleMapper sampleMapper;

    public SampleService(SampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }

    /**
     * 加锁
     *
     * @param lockName
     * @param lockTime
     */
    @DistributedLock(category = "sample", key = "#lockName")
    public void lockThenSleep(String lockName, Long lockTime) {
        try {
            Thread.sleep(lockTime);
        } catch (InterruptedException e) {
            throw QErrorUtil.sneakyError(e);
        }
    }

    /**
     * 查询数据
     *
     * @param sampleId
     * @return
     */
    public SampleDTO getSampleById(Long sampleId) {
        Sample sample = sampleMapper.selectById(sampleId);

        return QMapperUtil.mapTo(sample, SampleDTO.class);
    }

    /**
     * 保存数据
     *
     * @param createDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SampleDTO saveSample(SampleCreateDTO createDTO) {
        Sample sample = QMapperUtil.mapTo(createDTO, Sample.class);
        sampleMapper.insert(sample);

        return QMapperUtil.mapTo(sample, SampleDTO.class);
    }

    /**
     * 修改数据
     *
     * @param updateDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SampleDTO updateSample(SampleUpdateDTO updateDTO) {
        Sample samplePO = sampleMapper.selectById(updateDTO.getId());
        if (samplePO == null) {
            throw new DataNotFoundException(Sample.class, updateDTO.getId(), null);
        }

        QMapperUtil.mapFields(updateDTO, samplePO);
        BaseMapperHelper.updateById(sampleMapper, samplePO, true);

        return QMapperUtil.mapTo(samplePO, SampleDTO.class);
    }

}
