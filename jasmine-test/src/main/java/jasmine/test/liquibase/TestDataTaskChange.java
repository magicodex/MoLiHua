package jasmine.test.liquibase;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import jasmine.core.util.QErrorUtil;
import jasmine.test.liquibase.loader.DefaultTestDataLoader;
import jasmine.test.liquibase.loader.TestDataLoader;
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

import java.io.InputStream;
import java.lang.reflect.Constructor;

/**
 * @author mh.z
 */
@Component
public class TestDataTaskChange implements CustomTaskChange, ApplicationContextAware {
    private ResourceAccessor resourceAccessor;
    /** 类名 */
    private String className;
    /** 加载器名 */
    private String loaderName;
    /** 资源路径 */
    private String resourcePath;

    private static ApplicationContext applicationContext;

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
        TestDataTaskChange.applicationContext = applicationContext;
    }

    @Override
    public void execute(Database database) throws CustomChangeException {
        Assert.notNull(className, "className null");
        Assert.notNull(resourcePath, "resourcePath null");
        TestDataLoader loader = null;
        Class<?> type = ClassUtil.loadClass(className);

        if (StrUtil.isNotEmpty(loaderName)) {
            Class<TestDataLoader> clazz = ClassUtil.loadClass(loaderName);

            try {
                Constructor<TestDataLoader> constructor = clazz.getConstructor();
                loader = constructor.newInstance();
            } catch (Exception e) {
                throw QErrorUtil.sneakyError(e);
            }
        } else {
            loader = new DefaultTestDataLoader();
        }

        try (InputStream inputStream = resourceAccessor.openStream(null, resourcePath)) {
            loader.init(applicationContext, type);
            loader.load(resourcePath, inputStream);
        } catch (Exception e) {
            throw QErrorUtil.sneakyError(e);
        }
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
