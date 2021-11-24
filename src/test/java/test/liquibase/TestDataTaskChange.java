package test.liquibase;

import jasmine.common.util.QCheckUtil;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class TestDataTaskChange implements CustomTaskChange, ApplicationContextAware {
    private ResourceAccessor resourceAccessor;
    private String className;
    private String loaderName;
    private String resourcePath;

    private static ApplicationContext applicationContext;
    private static final String DEFAULT_LOADER_NAME = "defaultTestDataLoader";

    @Override
    public void execute(Database database) throws CustomChangeException {
        QCheckUtil.notNull(className, "className null");
        QCheckUtil.notNull(resourcePath, "resourcePath null");

        String loaderBeanName = (loaderName != null)
                ? loaderName
                : DEFAULT_LOADER_NAME;
        TestDataLoader loaderBean = (TestDataLoader) applicationContext.getBean(loaderBeanName);

        try (InputStream inputStream = resourceAccessor.openStream(null, resourcePath)) {
            loaderBean.loadData(inputStream, resourcePath, className);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setLoaderName(String loaderName) {
        this.loaderName = loaderName;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() throws SetupException {
        //
    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        this.resourceAccessor = resourceAccessor;
    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }

}
