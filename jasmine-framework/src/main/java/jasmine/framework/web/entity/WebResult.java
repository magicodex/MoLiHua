package jasmine.framework.web.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean success;
    /** 响应数据 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    /** 数据总数 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;
    /** 响应信息 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /** 错误代码 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;
    /** 错误详情 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorDetail;

    protected WebResult(Boolean success, T data, String message) {
        this(success, data, message, null, null);
    }

    protected WebResult(Boolean success, T data, String message,
                        String errorCode, String errorDetail) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
    }

    /**
     * 成功结果
     *
     * @param <T>
     * @return
     */
    public static <T> WebResult<T> success() {
        return new WebResult<>(true, null, null);
    }


    /**
     * 成功结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> WebResult<T> success(T data) {
        return new WebResult<>(true, data, null);
    }

    /**
     * 出错结果
     *
     * @param errorCode
     * @param message
     * @param <T>
     * @return
     */
    public static <T> WebResult<T> error(String errorCode, String message) {
        return new WebResult<>(false, null, message, errorCode, null);
    }

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
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

    /**
     * 转换成 ResponseEntity 对象
     *
     * @return
     */
    public ResponseEntity<WebResult<T>> toEntity() {
        ResponseEntity<WebResult<T>> entity = null;

        if (Boolean.TRUE.equals(success)) {
            entity = ResponseEntity.ok(this);
        } else {
            entity = new ResponseEntity<>(this, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return entity;
    }

    /**
     * 转换成 ResponseEntity 对象
     *
     * @return
     */
    public ResponseEntity<WebResult<T>> toOkEntity() {
        return ResponseEntity.ok(this);
    }

}
