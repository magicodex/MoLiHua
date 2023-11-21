package jasmine.framework.database.mybatisplus.i18n.injector;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.NewUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.framework.database.mybatisplus.annotation.I18n;
import jasmine.framework.database.mybatisplus.util.MybatisPlusColumnMapping;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author mh.z
 */
public class AbstractI18nSupportMethod extends AbstractMethod {
    private String sqlFormat;

    public AbstractI18nSupportMethod(String name, String sqlFormat) {
        super(name);
        this.sqlFormat = sqlFormat;
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String tableName = tableInfo.getTableName();
        StringBuilder fromPart = new StringBuilder(tableName);
        fromPart.append(" t left join ");
        fromPart.append(tableName);
        fromPart.append("_i18n t_i18n on (t.id = t_i18n.id and t_i18n.lang_code = #{_current.langCode})");

        String sql = String.format(sqlFormat, sqlFirst(), sqlSelectColumns(tableInfo, true), fromPart,
                sqlWhereEntityWrapper(true, tableInfo), sqlOrderBy(tableInfo), sqlComment());

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        MappedStatement mappedStatement = addSelectMappedStatementForTable(mapperClass, methodName, sqlSource, tableInfo);

        return mappedStatement;
    }

    @Override
    protected String sqlSelectColumns(TableInfo table, boolean queryWrapper) {
        /* 假设存在用户自定义的 resultMap 映射返回 */
        String selectColumns = ASTERISK;
        if (table.getResultMap() == null || table.isAutoInitResultMap()) {
            /* 未设置 resultMap 或者 resultMap 是自动构建的,视为属于mp的规则范围内 */
            selectColumns = table.getAllSqlSelect();
        }

        selectColumns = convertI18nSupportSelect(table.getEntityType(), selectColumns);

        if (!queryWrapper) {
            return selectColumns;
        }

        return convertChooseEwSelect(selectColumns);
    }

    /**
     *
     * @param entityType
     * @param selectColumns
     * @return
     */
    protected String convertI18nSupportSelect(Class<?> entityType, String selectColumns) {
        CheckUtil.notNull(entityType, "entityType null");

        if (StringUtil.isEmpty(selectColumns)) {
            return selectColumns;
        }

        Field[] entityFields = ReflectUtil.getFields(entityType);
        MybatisPlusColumnMapping columnMapping = new MybatisPlusColumnMapping(entityType);
        Set<String> i18nColumnNameSet = NewUtil.set();

        // 把多语言列添加到集合中
        for (Field field : entityFields) {
            I18n annotation = field.getAnnotation(I18n.class);

            if (annotation != null) {
                String columnName = columnMapping.getColumnName(field);
                i18nColumnNameSet.add(columnName);
            }
        }

        String[] columnArray = selectColumns.split(StringPool.COMMA, -1);
        StringBuilder columnBuffer = new StringBuilder();

        // 拼接新的查询列表
        for (int index = 0; index < columnArray.length; index++) {
            String column = columnArray[index];

            if (index > 0) {
                columnBuffer.append(StringPool.COMMA);
            }

            if (i18nColumnNameSet.contains(column)) {
                columnBuffer.append("(case when t_i18n.id is not null then t_i18n.");
                columnBuffer.append(column);
                columnBuffer.append(" else t.");
                columnBuffer.append(column);
                columnBuffer.append(" end) as ");
                columnBuffer.append(column);
            } else {
                columnBuffer.append("t.");
                columnBuffer.append(column);
            }
        }

        return columnBuffer.toString();
    }

}
