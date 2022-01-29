package jasmine.security.rbac.model;

/**
 * <p>
 * 功能。
 * </p>
 *
 * @author mh.z
 */
public class SecurityFunction {
    private Long id;
    /** 功能代码 */
    private String functionCode;
    /** 功能名称 */
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