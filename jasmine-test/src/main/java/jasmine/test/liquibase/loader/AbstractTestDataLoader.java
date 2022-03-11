package jasmine.test.liquibase.loader;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.test.liquibase.log.TestDataChangeLog;
import jasmine.test.liquibase.log.TestDataChangeLogMapper;
import jasmine.test.util.MybatisPlusUtil;
import jasmine.test.util.StreamUtil;
import org.springframework.context.ApplicationContext;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;

/**
 * @author mh.z
 */
public abstract class AbstractTestDataLoader implements TestDataLoader {
    /** 类型 */
    protected Class<?> type;
    /** 日志数据接口 */
    protected TestDataChangeLogMapper logMapper;

    @Override
    public void init(ApplicationContext applicationContext, Class<?> type) {
        this.type = type;
        this.logMapper = applicationContext.getBean(TestDataChangeLogMapper.class);
    }

    @Override
    public void load(String resourcePath, InputStream inputStream) {
        Assert.notNull(resourcePath, "resourcePath null");
        Assert.notNull(inputStream, "inputStream null");

        LambdaQueryWrapper<TestDataChangeLog> logWrapper = Wrappers.lambdaQuery();
        logWrapper.eq(TestDataChangeLog::getResourcePath, resourcePath);
        List<TestDataChangeLog> logList = logMapper.selectList(logWrapper);

        // 加载数据的逻辑是，1.先根据测试数据变更日志表里的记录删除原表里的记录。
        // 2.然后把新的数据插入到表里。

        if (CollUtil.isNotEmpty(logList)) {
            // 删除原来的数据
            List<Long> recordIdList = CollStreamUtil.toList(logList, TestDataChangeLog::getRecordId);
            deleteRecords(recordIdList);

            // 删除原来的日志记录
            List<Long> logIdList = CollStreamUtil.toList(logList, TestDataChangeLog::getId);
            logMapper.deleteBatchIds(logIdList);
        }

        List<Object> recordList = StreamUtil.readCSV(inputStream, (Class<Object>) type);

        if (CollUtil.isNotEmpty(recordList)) {
            for (Object record : recordList) {
                // 保存新的日志记录
                TestDataChangeLog log = createLog(resourcePath, record);
                logMapper.insert(log);
            }

            // 保存新的数据
            insertRecords(recordList);
        }
    }

    /**
     * 创建日志
     *
     * @param resourcePath
     * @param record
     * @return
     */
    protected TestDataChangeLog createLog(String resourcePath, Object record) {
        Assert.notNull(resourcePath, "resourcePath null");
        Assert.notNull(record, "record null");

        Function<Object, Long> function = (object) -> {
            Field field = MybatisPlusUtil.getIdField(type);

            if (field == null) {
                throw new RuntimeException("cannot find id field");
            }

            return (Long) ReflectUtil.getFieldValue(record, field);
        };

        return createLog(resourcePath, record, function);
    }

    /**
     * 创建日志
     *
     * @param resourcePath
     * @param record
     * @param function
     * @return
     */
    protected TestDataChangeLog createLog(String resourcePath, Object record, Function<Object, Long> function) {
        Assert.notNull(resourcePath, "resourcePath null");
        Assert.notNull(record, "record null");
        Assert.notNull(function, "function null");
        TestDataChangeLog log = new TestDataChangeLog();

        // 资源路径
        log.setResourcePath(resourcePath);
        // 资源名
        log.setResourceName(type.getName());
        // 记录ID
        Long resourceId = function.apply(record);
        log.setRecordId(resourceId);
        // 创建日期
        log.setCreatedDate(ZonedDateTime.now());
        // 最后更新日期
        log.setLastUpdatedDate(ZonedDateTime.now());

        return log;
    }

    /**
     * 保存记录
     *
     * @param records
     */
    protected abstract void insertRecords(List<Object> records);

    /**
     * 删除记录
     *
     * @param recordIds
     */
    protected abstract void deleteRecords(List<Long> recordIds);
}
