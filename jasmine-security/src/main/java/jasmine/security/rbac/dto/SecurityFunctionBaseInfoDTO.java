package jasmine.security.rbac.dto;

/**
 * @author mh.z
 */
public class SecurityFunctionBaseInfoDTO {
    /** 功能ID */
    private Long functionId;
    /** 功能代码 */
    private String functionCode;
    /** 功能名称 */
    private String functionName;

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
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
