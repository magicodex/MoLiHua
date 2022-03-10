package jasmine.framework.persistence.mybatisplus.wrapper;

/**
 * @author mh.z
 */
public class QueryWrapperEx<T> extends AbstractQueryWrapperEx<T, QueryWrapperEx<T>> {

    public static <T> QueryWrapperEx<T> wrapper() {
        return new QueryWrapperEx<>();
    }

}
