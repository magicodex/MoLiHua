package test.liquibase;

import java.io.InputStream;

public interface TestDataLoader {

    void loadData(InputStream inputStream, String resourcePath, String className);
}
