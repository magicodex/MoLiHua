package jasmine.framework.database.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import liquibase.util.StringUtil;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.baomidou.mybatisplus.core.enums.WrapperKeyword.APPLY;

/**
 * @author mh.z
 */
public class LambdaQueryWrapperWithI18n<T> extends LambdaQueryWrapperEx<T> {

    private static final String ENTITY_TABLE_COLUMN_NAME_PREFIX = "t.";
    private static final String I18N_TABLE_COLUMN_NAME_PREFIX = "t_i18n.";
    private static final String I18N_TABLE_ID_COLUMN_NAME = "t_i18n.id";

    public LambdaQueryWrapperWithI18n() {
        super();
    }

    public LambdaQueryWrapperWithI18n(T entity) {
        super(entity);
    }

    public LambdaQueryWrapperWithI18n(Class<T> entityClass) {
        super(entityClass);
    }

    protected LambdaQueryWrapperWithI18n(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
                                         Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
                                         SharedString paramAlias, SharedString lastSql,
                                         SharedString sqlComment, SharedString sqlFirst) {
        super(entity, entityClass, paramNameSeq, paramNameValuePairs, mergeSegments,
                paramAlias, lastSql, sqlComment, sqlFirst);
    }

    public static <T> LambdaQueryWrapperWithI18n<T> wrapper() {
        return new LambdaQueryWrapperWithI18n<>();
    }

    @Override
    protected LambdaQueryWrapperWithI18n<T> instance() {
        return new LambdaQueryWrapperWithI18n<>(getEntity(), getEntityClass(), paramNameSeq, paramNameValuePairs,
                new MergeSegments(), paramAlias, SharedString.emptyString(),
                SharedString.emptyString(), SharedString.emptyString());
    }

    @Override
    protected String columnToString(SFunction<T, ?> column, boolean onlyColumn) {
        String columnName = super.columnToString(column, onlyColumn);

        if (StringUtil.isEmpty(columnName)) {
            return columnName;
        }

        return ("t." + columnName);
    }

    /**
     * LIKE '%值%'
     *
     * @param condition
     * @param column
     * @param val
     * @return
     */
    public LambdaQueryWrapperWithI18n likeWithI18n(boolean condition, SFunction<T, ?> column, Object val) {
        return likeValueWithI18n(condition, SqlKeyword.LIKE, column, val, SqlLike.DEFAULT);
    }

    /**
     *
     * @param condition
     * @param keyword
     * @param column
     * @param val
     * @param sqlLike
     * @return
     */
    protected LambdaQueryWrapperWithI18n likeValueWithI18n(boolean condition, SqlKeyword keyword, SFunction<T, ?> column,
                                                           Object val, SqlLike sqlLike) {
        maybeDo(condition, () -> {
            QueryWrapperEx<T> nestedWrapper = new QueryWrapperEx(getEntity(), getEntityClass(), paramNameSeq, paramNameValuePairs,
                    new MergeSegments(), paramAlias, SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());

            nestedWrapper.nested((wrapper) -> {
                wrapper.isNull(I18N_TABLE_ID_COLUMN_NAME);

                String columnName = ENTITY_TABLE_COLUMN_NAME_PREFIX + super.columnToString(column, true);
                if (sqlLike == SqlLike.LEFT) {
                    wrapper.likeLeft(columnName, val);
                } else if (sqlLike == SqlLike.RIGHT) {
                    wrapper.likeRight(columnName, val);
                } else {
                    wrapper.like(columnName, val);
                }
            });

            nestedWrapper.or((wrapper) -> {
                wrapper.isNotNull(I18N_TABLE_ID_COLUMN_NAME);

                String columnName = I18N_TABLE_COLUMN_NAME_PREFIX + super.columnToString(column, true);
                if (sqlLike == SqlLike.LEFT) {
                    wrapper.likeLeft(columnName, val);
                } else if (sqlLike == SqlLike.RIGHT) {
                    wrapper.likeRight(columnName, val);
                } else {
                    wrapper.like(columnName, val);
                }
            });

            appendSqlSegments(APPLY, nestedWrapper);
        });

        return this;
    }

}
