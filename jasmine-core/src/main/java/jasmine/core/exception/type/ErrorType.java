package jasmine.core.exception.type;

/**
 * 错误类型
 *
 * @author mh.z
 */
public interface ErrorType {

    /**
     * 返回错误代码
     *
     * @return
     */
    String getCode();

    /**
     * 返回错误详情
     *
     * @return
     */
    String getDetail();
}
