package jasmine.test.liquibase.loader;

import org.springframework.context.ApplicationContext;

import java.io.InputStream;

/**
 * <p>
 * 测试数据加载器。
 * </p>
 *
 * @author mh.z
 */
public interface TestDataLoader {

    /**
     * 初始
     *
     * @param
     * @param type
     */
    void init(ApplicationContext applicationContext, Class<?> type);

    /**
     * 加载数据
     *
     * @param resourcePath
     * @param inputStream
     */
    void load(String resourcePath, InputStream inputStream);
}
