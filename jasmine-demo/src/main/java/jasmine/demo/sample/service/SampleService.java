package jasmine.demo.sample.service;

import jasmine.core.util.QErrorUtil;
import jasmine.demo.sample.entity.Sample;
import jasmine.demo.sample.mapper.SampleMapper;
import jasmine.framework.lock.annotation.DistributedLock;
import jasmine.framework.persistence.mybatisplus.BaseMapperHelper;
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
    public Sample getSampleById(Long sampleId) {
        return sampleMapper.selectById(sampleId);
    }

    /**
     * 保存数据
     *
     * @param sample
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Sample saveSample(Sample sample) {
        sampleMapper.insert(sample);

        return sample;
    }

    /**
     * 修改数据
     *
     * @param sample
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Sample updateSample(Sample sample) {
        BaseMapperHelper.strictUpdateById(sampleMapper, sample);

        return sample;
    }

}
