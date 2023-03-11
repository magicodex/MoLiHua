package jasmine.framework.mybatis.testdependency.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.mybatis.annotation.I18n;
import jasmine.framework.mybatis.entity.BaseI18nEntity;
import jasmine.q.CryptoTypeHandler;

/**
 * @author mh.z
 */
@TableName(value = "test_entity1", autoResultMap = true)
public class TestEntity1 extends BaseI18nEntity {

    @TableField("code_1")
    String code1;

    @I18n
    @TableField("name_1")
    String name1;

    @TableField("attr_1")
    String attr1;

    @TableField(value = "secret_1", typeHandler = CryptoTypeHandler.class)
    String secret1;

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

    public String getSecret1() {
        return secret1;
    }

    public void setSecret1(String secret1) {
        this.secret1 = secret1;
    }

}
