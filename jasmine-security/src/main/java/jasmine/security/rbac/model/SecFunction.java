package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.entity.BaseEntity;

/**
 * <p>
 * 功能。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_function")
public class SecFunction extends BaseEntity {

    /** 功能代码 */
    @TableField("function_code")
    private String functionCode;

    /** 功能名称 */
    @TableField("function_name")
    private String functionName;

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

}
