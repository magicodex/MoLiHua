package jasmine.framework.database.mybatisplus.injector;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author mh.z
 */
public class I18nSupportSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);

        Class<?> entityType = tableInfo.getEntityType();
        if (!BaseI18nEntity.class.isAssignableFrom(entityType)) {
            return methodList;
        }

        Stream.Builder<AbstractMethod> builder = Stream.<AbstractMethod>builder()
                .add(new I18nSupportSelectList())
                .add(new I18nSupportSelectPage());
        List<AbstractMethod> newMethodList = builder.build().collect(toList());

        return CollectionUtil.unionAll(methodList, newMethodList);
    }

}
