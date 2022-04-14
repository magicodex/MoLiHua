package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.framework.persistence.constant.MapperConstants;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class I18nRecordTest {

    @Test
    public void testToI18nRecordMap() {
        List<Map> mapList = new ArrayList<>();
        mapList.add(Map.of(MapperConstants.SQL_COLUMN_ID, 1L,
                MapperConstants.SQL_COLUMN_VERSION_NUMBER, 1,
                "name_1", "name1Value"));
        mapList.add(Map.of(MapperConstants.SQL_COLUMN_ID, 2L,
                MapperConstants.SQL_COLUMN_VERSION_NUMBER, 10,
                "name_2", "name2Value"));

        Map<Long, I18nRecord> actualMap = I18nRecord.toI18nRecordMap(mapList);
        Assert.assertEquals(2, actualMap.size());

        Assert.assertEquals(Long.valueOf(1L), actualMap.get(1L).getId());
        Assert.assertEquals(Integer.valueOf(1), actualMap.get(1L).getVersionNumber());
        Assert.assertEquals("name1Value", actualMap.get(1L).getValueAsString("name_1"));
        Assert.assertEquals(Long.valueOf(2L), actualMap.get(2L).getId());
        Assert.assertEquals(Integer.valueOf(10), actualMap.get(2L).getVersionNumber());
        Assert.assertEquals("name2Value", actualMap.get(2L).getValueAsString("name_2"));
    }

}
