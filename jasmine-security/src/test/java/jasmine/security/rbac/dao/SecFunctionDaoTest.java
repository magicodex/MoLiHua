package jasmine.security.rbac.dao;

import jasmine.core.util.QCollUtil;
import jasmine.framework.test.context.AppTestContext;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import jasmine.security.rbac.mapper.SecFunctionMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class SecFunctionDaoTest extends AppTestContext {
    @Autowired
    private SecFunctionMapper secFunctionMapper;

    @Test
    public void testListAllTenantFunctionBaseInfoDTOsByRoleIdsNoI18n() {
        SecFunctionDAO dao = new SecFunctionDAO(secFunctionMapper);

        // 角色1被授予功能1和功能2
        {
            List<SecFunctionBaseInfoDTO> actualList = dao
                    .listAllTenantFunctionBaseInfoDTOsByRoleIdsNoI18n(Collections.singletonList(100001L));
            Assert.assertNotNull(actualList);
            Assert.assertEquals(2, actualList.size());

            Map<String, SecFunctionBaseInfoDTO> actualMap = QCollUtil.toMap(actualList,
                    SecFunctionBaseInfoDTO::getFunctionCode);
            Assert.assertTrue(actualMap.containsKey("FUNCTION_1"));
            Assert.assertTrue(actualMap.containsKey("FUNCTION_2"));
        }

        // 角色2被授予的功能2
        {
            List<SecFunctionBaseInfoDTO> actualList = dao
                    .listAllTenantFunctionBaseInfoDTOsByRoleIdsNoI18n(Collections.singletonList(100002L));
            Assert.assertNotNull(actualList);
            Assert.assertEquals(1, actualList.size());

            Map<String, SecFunctionBaseInfoDTO> actualMap = QCollUtil.toMap(actualList,
                    SecFunctionBaseInfoDTO::getFunctionCode);
            Assert.assertTrue(actualMap.containsKey("FUNCTION_2"));
        }
    }

    @Test
    public void testListFunctionBaseInfoDTOsByIdNoI18n() {
        SecFunctionDAO dao = new SecFunctionDAO(secFunctionMapper);

        // 资源1被授予给功能1
        {
            List<SecFunctionBaseInfoDTO> actualList = dao
                    .listFunctionBaseInfoDTOsByIdNoI18n(100001L);
            Assert.assertNotNull(actualList);
            Assert.assertEquals(1, actualList.size());

            Map<String, SecFunctionBaseInfoDTO> actualMap = QCollUtil.toMap(actualList,
                    SecFunctionBaseInfoDTO::getFunctionCode);
            Assert.assertTrue(actualMap.containsKey("FUNCTION_1"));
        }

        // 资源5被授予给功能2
        {
            List<SecFunctionBaseInfoDTO> actualList = dao
                    .listFunctionBaseInfoDTOsByIdNoI18n(100005L);
            Assert.assertNotNull(actualList);
            Assert.assertEquals(1, actualList.size());

            Map<String, SecFunctionBaseInfoDTO> actualMap = QCollUtil.toMap(actualList,
                    SecFunctionBaseInfoDTO::getFunctionCode);
            Assert.assertTrue(actualMap.containsKey("FUNCTION_2"));
        }

        // 资源7被授予给功能2
        {
            List<SecFunctionBaseInfoDTO> actualList = dao
                    .listFunctionBaseInfoDTOsByIdNoI18n(100007L);
            Assert.assertNotNull(actualList);
            Assert.assertEquals(1, actualList.size());

            Map<String, SecFunctionBaseInfoDTO> actualMap = QCollUtil.toMap(actualList,
                    SecFunctionBaseInfoDTO::getFunctionCode);
            Assert.assertTrue(actualMap.containsKey("FUNCTION_2"));
        }

        // 资源8未被授予给功能
        {
            List<SecFunctionBaseInfoDTO> actualList = dao
                    .listFunctionBaseInfoDTOsByIdNoI18n(100008L);
            Assert.assertNotNull(actualList);
            Assert.assertEquals(0, actualList.size());
        }
    }

}
