package jasmine.framework.persistence.mybatisplus.testdependency;

import com.baomidou.mybatisplus.annotation.TableField;
import jasmine.framework.persistence.annotation.I18n;

/**
 * @author mh.z
 */
public class TestEntity2 extends TestEntity1 {

    @TableField("field_4")
    String field4;

    @I18n
    @TableField("field_5")
    String field5;

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

}
