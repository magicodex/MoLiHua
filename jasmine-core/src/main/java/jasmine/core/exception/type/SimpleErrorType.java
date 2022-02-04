package jasmine.core.exception.type;

/**
 * @author mh.z
 */
public class SimpleErrorType implements ErrorType {
    /** 错误代码 */
    private String code;
    /** 错误详情 */
    private String detail;

    public SimpleErrorType(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDetail() {
        return detail;
    }

}
