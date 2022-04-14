package jasmine.framework.persistence.mybatisplus.testdependency.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.annotation.I18n;

/**
 * @author mh.z
 */
@TableName("test_entity2")
public class TestEntity2 extends TestEntity1 {

    @TableField("code_2")
    String code2;

    @I18n
    @TableField("name_2")
    String name2;

    @TableField("attr_2")
    String attr2;

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

}
