package jasmine.framework.common.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

/**
 * <p>
 * 映射对象工具类。
 * </p>
 *
 * @author mh.z
 */
public class MapperUtil {
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

    /**
     * 映射字段的值
     *
     * @param source
     * @param destination
     * @return
     */
    public static <S, D> D mapFields(S source, D destination) {
        MAPPER_FACADE.map(source, destination);

        return destination;
    }

}
