package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.core.util.QCollUtil;
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

    private static final String I18N_TABLE = "test_entity1_i18n";

    @Test
    public void testInsert() {
        I18nCRUD crud = new I18nCRUD(sqlSession, I18N_TABLE);

        Map<String, String> data = new HashMap<>() {{
            put("name", "名称1");
            put("desc", "描述1");
        }};
        // 新增多语言记录
        int rowCount = crud.insert(1L, "zh-CN", data, true);
        Assert.assertEquals(1, rowCount);

        // 应当只会获取到一条记录
        List<I18nRecord> recordList = selectI18n(Collections.singletonList(1L), null);
        Assert.assertEquals(1, recordList.size());

        I18nRecord record = QCollUtil.getFirst(recordList);
        Assert.assertEquals(Long.valueOf(1), record.getId());
        Assert.assertEquals("zh-CN", record.getLangCode());
        Assert.assertTrue(record.getDefaultFlag());
        Assert.assertEquals("名称1", record.getValueAsString("name"));
        Assert.assertEquals("描述1", record.getValueAsString("desc"));
    }

    @Test
    public void testUpdate() {
        I18nCRUD crud = new I18nCRUD(sqlSession, I18N_TABLE);
        initI18n(1L);

        Map<String, String> data = new HashMap<>() {{
            put("name", "名称2");
            put("desc", "描述2");
        }};
        // 新增多语言记录
        int rowCount = crud.update(1L, "zh-CN", data, null);
        Assert.assertEquals(1, rowCount);

        // 中文多语言应当被修改
        {
            List<I18nRecord> recordList = selectI18n(Collections.singletonList(1L), "zh-CN");
            Assert.assertEquals(1, recordList.size());

            I18nRecord record = QCollUtil.getFirst(recordList);
            Assert.assertTrue(record.getDefaultFlag());
            Assert.assertEquals("名称2", record.getValueAsString("name"));
            Assert.assertEquals("描述2", record.getValueAsString("desc"));
        }

        // 英文多语言应当未被修改
        {
            List<I18nRecord> recordList = selectI18n(Collections.singletonList(1L), "en-US");
            Assert.assertEquals(1, recordList.size());

            I18nRecord record = QCollUtil.getFirst(recordList);
            Assert.assertFalse(record.getDefaultFlag());
            Assert.assertEquals("name1", record.getValueAsString("name"));
            Assert.assertEquals("desc1", record.getValueAsString("desc"));
        }
    }

    @Test
    public void testDelete() {
        // TODO
    }

    @Test
    public void testSelect() {
        // TODO
    }

    @Test
    public void testSelectDefault() {
        // TODO
    }

    /**
     * 初始多语言数据
     *
     * @param id
     */
    private void initI18n(Long id) {
        I18nCRUD crud = new I18nCRUD(sqlSession, I18N_TABLE);

        {
            Map<String, String> data = new HashMap<>() {{
                put("name", "名称1");
                put("desc", "描述1");
            }};

            // 新增多语言记录
            crud.insert(1L, "zh-CN", data, true);
        }

        {
            Map<String, String> data = new HashMap<>() {{
                put("name", "name1");
                put("desc", "desc1");
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
            put(I18nCRUD.PARAM_IDS, ids);
            put(I18nCRUD.PARAM_LANG_CODE, langCode);
        }};

        List<Map> recordList = sqlSession.selectOne(I18nCRUD.STATEMENT_SELECT, parameter);
        List<I18nRecord> i18nRecordList = QCollUtil.mapToList(recordList, (record) -> {
            return new I18nRecord(record);
        });

        return i18nRecordList;
    }

}
