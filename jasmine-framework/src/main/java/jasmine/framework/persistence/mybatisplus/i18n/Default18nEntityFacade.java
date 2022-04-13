package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.framework.persistence.entity.BaseI18nEntity;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nFieldMap;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mh.z
 */
public class Default18nEntityFacade implements I18nEntityFacade {
    private static final Log mybatisLog = LogFactory.getLog(Default18nEntityFacade.class);
    private SqlSession sqlSession;
    private Map<Class<?>, I18nFieldMap> i18nMetas;

    private static final String INSERT_STATEMENT = "jasmine.EntityI18n.insertI18n";
    private static final String UPDATE_STATEMENT = "jasmine.EntityI18n.updateI18n";
    private static final String DELETE_STATEMENT = "jasmine.EntityI18n.deleteI18n";
    private static final String SELECT_STATEMENT = "jasmine.EntityI18n.selectI18n";

    public Default18nEntityFacade(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.i18nMetas = new ConcurrentHashMap<>();
    }

    @Override
    public int insertI18n(Collection<? extends BaseI18nEntity> entities) {
        // TODO
        return 0;
    }

    @Override
    public int updateI18n(Collection<? extends BaseI18nEntity> entities) {
        // TODO
        return 0;
    }

    @Override
    public int deleteI18n(Collection<? extends Serializable> ids) {
        // TODO
        return 0;
    }

    @Override
    public <T> List<T> populateI18n(Collection<T> collection, Class<? extends BaseI18nEntity> entityType) {
        // TODO
        return null;
    }

}
