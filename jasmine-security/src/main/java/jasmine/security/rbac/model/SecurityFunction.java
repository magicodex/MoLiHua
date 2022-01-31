package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 功能。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_function")
public class SecurityFunction {
    @TableId
    private Long id;

    /** 功能代码 */
    @TableField("function_code")
    private String functionCode;

    /** 功能名称 */
    @TableField("function_name")
    private String functionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
