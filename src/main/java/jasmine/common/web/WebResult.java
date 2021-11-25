package jasmine.common.web;

/**
 * <p>
 * WEB响应结果。
 * </p>
 *
 * @author mh.z
 * @param <T>
 */
public class WebResult<T> {
    /** 是否成功 */
    private Boolean success;
    /** 响应数据 */
    private T data;
    /** 响应信息 */
    private String message;

    /** 错误代码 */
    private String errorCode;
    /** 错误详情 */
    private String errorDetail;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

}
