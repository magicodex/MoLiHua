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

    /** 实体类的名称后缀 */
    private static final String ENTITY_SUFFIX_1 = "DO";
    private static final String ENTITY_SUFFIX_2 = "EO";
    /** mapper的名称后缀 */
    private static final String MAPPER_SUFFIX = "Mapper";

    @Override
    public void init(Class<?> type) {
        super.init(type);

        String mapperBeanName = getMapperBeanName(type);
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

    /**
     * 返回指定类型对应的 mapper 的名称
     *
     * @param type
     * @return
     */
    protected String getMapperBeanName(Class<?> type) {
        Assert.notNull(type, "type null");

        String simpleClassName = type.getSimpleName();
        String mapperPrefix = StrUtil.lowerFirst(simpleClassName);

        if (mapperPrefix.endsWith(ENTITY_SUFFIX_1)
                || mapperPrefix.endsWith(ENTITY_SUFFIX_2)) {
            mapperPrefix = mapperPrefix.substring(0, mapperPrefix.length() - 2);
        }

        String mapperBeanName = mapperPrefix + MAPPER_SUFFIX;
        return mapperBeanName;
    }

}
