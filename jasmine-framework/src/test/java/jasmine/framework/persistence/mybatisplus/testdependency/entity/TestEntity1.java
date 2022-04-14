package jasmine.framework.persistence.mybatisplus.testdependency.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.annotation.I18n;
import jasmine.framework.persistence.entity.BaseI18nEntity;

/**
 * @author mh.z
 */
@TableName("test_entity1")
public class TestEntity1 extends BaseI18nEntity {

    @TableField("code_1")
    String code1;

    @I18n
    @TableField("name_1")
    String name1;

    @TableField("attr_1")
    String attr1;

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

}
