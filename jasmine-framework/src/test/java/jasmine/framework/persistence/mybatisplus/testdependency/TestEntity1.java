package jasmine.framework.persistence.mybatisplus.testdependency;

import com.baomidou.mybatisplus.annotation.TableField;
import jasmine.framework.persistence.annotation.I18n;
import jasmine.framework.persistence.entity.BaseI18nEntity;

/**
 * @author mh.z
 */
public class TestEntity1 extends BaseI18nEntity {

    @TableField("field_1")
    String field1;

    @I18n
    @TableField("field_2")
    String field2;

    @TableField("field_3")
    String field3;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

}
