package test.liquibase.loader;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import test.liquibase.log.TestDataChangeLog;
import test.liquibase.log.TestDataChangeLogMapper;
import test.util.MybatisPlusUtil;
import test.util.StreamUtil;
import test.util.SpringUtil;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractTestDataLoader implements TestDataLoader {
    protected Class<?> type;
    protected TestDataChangeLogMapper logMapper;

    @Override
    public void init(Class<?> type) {
        this.type = type;
        this.logMapper = SpringUtil.getBean(TestDataChangeLogMapper.class);
    }

    @Override
    public void load(String resourcePath, InputStream inputStream) {
        Assert.notNull(resourcePath, "resourcePath null");
        Assert.notNull(inputStream, "inputStream null");

        LambdaQueryWrapper<TestDataChangeLog> logWrapper = Wrappers.lambdaQuery();
        logWrapper.eq(TestDataChangeLog::getResourcePath, resourcePath);
        List<TestDataChangeLog> logList = logMapper.selectList(logWrapper);

        if (CollUtil.isNotEmpty(logList)) {
            List<Long> recordIdList = CollStreamUtil.toList(logList, TestDataChangeLog::getRecordId);
            deleteRecords(recordIdList);

            List<Long> logIdList = CollStreamUtil.toList(logList, TestDataChangeLog::getId);
            logMapper.deleteBatchIds(logIdList);
        }

        List<Object> recordList = StreamUtil.readCSV(inputStream, (Class<Object>) type);

        if (CollUtil.isNotEmpty(recordList)) {
            for (Object record : recordList) {
                TestDataChangeLog log = createLog(resourcePath, record);
                logMapper.insert(log);
            }

            insertRecords(recordList);
        }
    }

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

    protected TestDataChangeLog createLog(String resourcePath, Object record, Function<Object, Long> function) {
        Assert.notNull(resourcePath, "resourcePath null");
        Assert.notNull(record, "record null");
        Assert.notNull(function, "function null");
        TestDataChangeLog log = new TestDataChangeLog();

        log.setResourcePath(resourcePath);
        log.setResourceName(type.getName());
        Long resourceId = function.apply(record);
        log.setRecordId(resourceId);
        log.setCreatedDate(ZonedDateTime.now());
        log.setLastUpdatedDate(ZonedDateTime.now());

        return log;
    }

    protected abstract void insertRecords(List<Object> records);

    protected abstract void deleteRecords(List<Long> recordIds);
}
