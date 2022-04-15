package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.framework.persistence.mybatisplus.i18n.support.I18nRecord;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public interface I18nRecordFacade {

    /**
     * 新增多语言
     *
     * @param sqlSession
     * @param tableName
     * @param id
     * @param langCode
     * @param data
     * @return
     */
    int insert(SqlSession sqlSession, String tableName, Long id,
               String langCode, Map<String, String> data);

    /**
     * 更新多语言
     *
     * @param sqlSession
     * @param tableName
     * @param id
     * @param langCode
     * @param data
     * @param versionNumber
     * @return
     */
    int update(SqlSession sqlSession, String tableName, Long id,
               String langCode, Map<String, String> data, Integer versionNumber);

    /**
     * 删除多语言
     *
     * @param sqlSession
     * @param tableName
     * @param ids
     * @param langCode
     * @return
     */
    int delete(SqlSession sqlSession, String tableName,
               Collection<? extends Serializable> ids, String langCode);

    /**
     * 查询多语言
     *
     * @param sqlSession
     * @param tableName
     * @param ids
     * @param langCode
     * @return
     */
    List<I18nRecord> select(SqlSession sqlSession, String tableName,
                            Collection<? extends Serializable> ids, String langCode);
}
