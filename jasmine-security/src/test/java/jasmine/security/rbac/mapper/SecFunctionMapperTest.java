package jasmine.security.rbac.mapper;

import jasmine.framework.test.context.AppTestContext;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class SecFunctionMapperTest extends AppTestContext {
    @Autowired
    private SecFunctionMapper testMapper;

    @Test
    public void testListAllTenantFunctionBaseInfoDTOsByRoleIdsNoI18n() {
        List<SecFunctionBaseInfoDTO> actualList = testMapper
                .listAllTenantFunctionBaseInfoDTOsByRoleIdsNoI18n(Collections.singletonList(100001L));

        Assert.assertNotNull(actualList);
    }

    @Test
    public void testListFunctionBaseInfoDTOsByIdNoI18n() {
        List<SecFunctionBaseInfoDTO> actualList = testMapper
                .listFunctionBaseInfoDTOsByIdNoI18n(100001L);

        Assert.assertNotNull(actualList);
    }

}
