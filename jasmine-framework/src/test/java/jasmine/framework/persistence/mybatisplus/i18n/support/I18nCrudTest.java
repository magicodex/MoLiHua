package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.core.util.CollUtil;
import jasmine.framework.testdependency.context.FrameworkTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class I18nCrudTest extends FrameworkTestContext {
    @Autowired
    private SqlSessionTemplate sqlSession;

    private static final String I18N_TABLE = "test_entity2_i18n";

    @Test
    public void testInsert() {
        I18nCrud crud = new I18nCrud(sqlSession, I18N_TABLE);

        Map<String, String> data = new HashMap<>() {{
            put("name_1", "名称1");
            put("name_2", "名称2");
        }};
        // 新增多语言记录
        int rowCount = crud.insert(1L, "zh-CN", data, true);
        Assert.assertEquals(1, rowCount);

        // 应当只会获取到一条记录
        List<I18nRecord> recordList = selectI18n(Collections.singletonList(1L), null);
        Assert.assertEquals(1, recordList.size());

        I18nRecord record = CollUtil.getFirst(recordList);
        Assert.assertEquals(Long.valueOf(1), record.getId());
        Assert.assertEquals("zh-CN", record.getLangCode());
        Assert.assertTrue(record.getDefaultFlag());
        Assert.assertEquals("名称1", record.getValueAsString("name_1"));
        Assert.assertEquals("名称2", record.getValueAsString("name_2"));
    }

    @Test
    public void testUpdate() {
        I18nCrud crud = new I18nCrud(sqlSession, I18N_TABLE);
        initI18n(1L);

        Map<String, String> data = new HashMap<>() {{
            put("name_1", "新名称1");
            put("name_2", "新名称2");
        }};
        // 新增多语言记录
        int rowCount = crud.update(1L, "zh-CN", data, null);
        Assert.assertEquals(1, rowCount);

        // 中文多语言应当被修改
        {
            List<I18nRecord> recordList = selectI18n(Collections.singletonList(1L), "zh-CN");
            Assert.assertEquals(1, recordList.size());

            I18nRecord record = CollUtil.getFirst(recordList);
            Assert.assertTrue(record.getDefaultFlag());
            Assert.assertEquals("新名称1", record.getValueAsString("name_1"));
            Assert.assertEquals("新名称2", record.getValueAsString("name_2"));
        }

        // 英文多语言应当未被修改
        {
            List<I18nRecord> recordList = selectI18n(Collections.singletonList(1L), "en-US");
            Assert.assertEquals(1, recordList.size());

            I18nRecord record = CollUtil.getFirst(recordList);
            Assert.assertFalse(record.getDefaultFlag());
            Assert.assertEquals("name1", record.getValueAsString("name_1"));
            Assert.assertEquals("name2", record.getValueAsString("name_2"));
        }
    }

    @Test
    public void testDelete() {
        I18nCrud crud = new I18nCrud(sqlSession, I18N_TABLE);
        initI18n(1L);

        {
            // 删除前应当查询到两条
            List<I18nRecord> recordList = selectI18n(Collections.singletonList(1L), null);
            Assert.assertEquals(2, recordList.size());
        }

        // 删除指定的多语言记录
        int rowCount = crud.delete(Collections.singletonList(1L), null);
        Assert.assertEquals(2, rowCount);

        {
            // 删除后应当查询不到记录
            List<I18nRecord> recordList = selectI18n(Collections.singletonList(1L), null);
            Assert.assertEquals(0, recordList.size());
        }
    }

    @Test
    public void testSelect() {
        I18nCrud crud = new I18nCrud(sqlSession, I18N_TABLE);
        initI18n(1L);

        {
            List<I18nRecord> recordList = crud.select(Collections.singletonList(1L), null);
            Assert.assertEquals(2, recordList.size());
        }

        {
            List<I18nRecord> recordList = crud.select(Collections.singletonList(1L), "zh-CN");
            Assert.assertEquals(1, recordList.size());
        }
    }

    @Test
    public void testSelectDefault() {
        I18nCrud crud = new I18nCrud(sqlSession, I18N_TABLE);
        initI18n(1L);

        List<I18nRecord> recordList = crud.selectDefault(Collections.singletonList(1L));
        Assert.assertEquals(1, recordList.size());

        I18nRecord record = CollUtil.getFirst(recordList);
        Assert.assertEquals("zh-CN", record.getLangCode());
    }

    /**
     * 初始多语言数据
     *
     * @param id
     */
    private void initI18n(Long id) {
        I18nCrud crud = new I18nCrud(sqlSession, I18N_TABLE);

        {
            Map<String, String> data = new HashMap<>() {{
                put("name_1", "名称1");
                put("name_2", "名称2");
            }};

            // 新增多语言记录
            crud.insert(1L, "zh-CN", data, true);
        }

        {
            Map<String, String> data = new HashMap<>() {{
                put("name_1", "name1");
                put("name_2", "name2");
            }};
            // 新增多语言记录
            crud.insert(1L, "en-US", data, false);
        }
    }

    /**
     * 查询多语言数据
     *
     * @param ids
     * @param langCode
     * @return
     */
    private List<I18nRecord> selectI18n(Collection<Long> ids, String langCode) {
        Object parameter = new HashMap<>() {{
            put(I18nCrud.PARAM_TABLE, I18N_TABLE);
            put(I18nCrud.PARAM_IDS, ids);
            put(I18nCrud.PARAM_LANG_CODE, langCode);
        }};

        List<Map> recordList = sqlSession.selectList(I18nCrud.STATEMENT_SELECT, parameter);
        List<I18nRecord> i18nRecordList = CollUtil.mapToList(recordList, (record) -> {
            return new I18nRecord(record);
        });

        return i18nRecordList;
    }

}
