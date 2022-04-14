package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.framework.testdependency.context.FrameworkTestContext;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class DefaultI18nEntityFacadeTest extends FrameworkTestContext {
    @Autowired
    private SqlSession sqlSession;

    @Test
    public void testInsertI18n() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSession);
    }

}
