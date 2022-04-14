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
     * 插入多语言
     *
     * @param session
     * @param table
     * @param id
     * @param language
     * @param data
     * @return
     */
    int insert(SqlSession session, String table, Long id,
               String language, Map<String, String> data);

    /**
     * 修改多语言
     *
     * @param session
     * @param table
     * @param id
     * @param language
     * @param data
     * @param version
     * @return
     */
    int update(SqlSession session, String table, Long id,
               String language, Map<String, String> data, Integer version);

    /**
     * 删除多语言
     *
     * @param session
     * @param table
     * @param ids
     * @param language
     * @return
     */
    int delete(SqlSession session, String table, Collection<? extends Serializable> ids,
               String language);

    /**
     * 查询多语言
     *
     * @param session
     * @param table
     * @param ids
     * @param language
     * @return
     */
    List<I18nRecord> select(SqlSession session, String table, Collection<? extends Serializable> ids,
                            String language);
}
