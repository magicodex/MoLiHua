package test.liquibase.loader;

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
     * @param type
     */
    void init(Class<?> type);

    /**
     * 加载数据
     *
     * @param resourcePath
     * @param inputStream
     */
    void load(String resourcePath, InputStream inputStream);
}
