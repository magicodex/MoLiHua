package jasmine.core.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

/**
 * @author mh.z
 */
public class QMapperUtil {
    private static final MapperFacade MAPPER_FACADE;

    static {
        DefaultMapperFactory.Builder builder = new DefaultMapperFactory.Builder();
        MapperFactory mapperFactory = builder.build();
        MAPPER_FACADE = mapperFactory.getMapperFacade();
    }

    /**
     * 映射成指定类型
     *
     * @param source
     * @param targetType
     * @param <T>
     */
    public static <T> T mapTo(Object source, Class<T> targetType) {
        return MAPPER_FACADE.map(source, targetType);
    }

    /**
     * 映射成指定类型
     *
     * @param source
     * @param targetType
     * @param <T>
     */
    public static <T> List<T> mapToList(Iterable<?> source, Class<T> targetType) {
        return MAPPER_FACADE.mapAsList(source, targetType);
    }

}
