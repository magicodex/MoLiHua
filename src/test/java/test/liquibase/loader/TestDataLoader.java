package test.liquibase.loader;

import java.io.InputStream;

public interface TestDataLoader {

    void init(Class<?> type);

    void load(String resourcePath, InputStream inputStream);
}
