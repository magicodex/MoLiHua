package jasmine.test.liquibase.loader;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.core.util.QSpringUtil;

import java.util.List;

/**
 * @author mh.z
 */
public class DefaultTestDataLoader extends AbstractTestDataLoader {
    private BaseMapper<Object> baseMapper;

    @Override
    public void init(Class<?> type) {
        super.init(type);

        String simpleClassName = type.getSimpleName();
        String mapperPrefix = StrUtil.lowerFirst(simpleClassName);

        if (mapperPrefix.endsWith("DO")) {
            mapperPrefix = mapperPrefix.substring(0, mapperPrefix.length() - 2);
        }

        String mapperBeanName = mapperPrefix + "Mapper";
        baseMapper = QSpringUtil.getBean(mapperBeanName);
    }

    @Override
    protected void insertRecords(List<Object> records) {
        Assert.notNull(records, "records null");

        for (Object record : records) {
            baseMapper.insert(record);
        }
    }

    @Override
    protected void deleteRecords(List<Long> recordIds) {
        Assert.notNull(recordIds, "recordIds null");

        baseMapper.deleteBatchIds(recordIds);
    }

}