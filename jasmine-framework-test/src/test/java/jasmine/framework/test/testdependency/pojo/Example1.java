package jasmine.framework.test.testdependency.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.mybatis.entity.BaseEntity;

/**
 * @author mh.z
 */
@TableName("test_example1")
public class Example1 extends BaseEntity {

    @TableField("attribute1")
    private String attribute1;

    @TableField("attribute2")
    private Integer attribute2;

    @TableField("attribute3")
    private Boolean attribute3;

    @TableField("tenant_id")
    private Long tenantId;

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public Integer getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(Integer attribute2) {
        this.attribute2 = attribute2;
    }

    public Boolean getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(Boolean attribute3) {
        this.attribute3 = attribute3;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
