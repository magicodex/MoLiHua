package jasmine.framework.database.liquibase;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import jasmine.framework.common.util.ErrorUtil;
import jasmine.framework.common.util.SpringUtil;
import jasmine.framework.database.mybatisplus.util.MybatisPlusCsvUtil;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author mh.z
 */
public class BeanTaskChange implements CustomTaskChange, ApplicationContextAware {
    private ResourceAccessor resourceAccessor;
    /** bean名称 */
    private String beanName;
    /** 方法名称 */
    private String methodName;
    /** 类型名称 */
    private String typeName;
    /** 资源路径 */
    private String resourcePath;

    private static ApplicationContext applicationContext;

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanTaskChange.applicationContext = applicationContext;
    }

    @Override
    public void execute(Database database) throws CustomChangeException {
        Assert.notNull(beanName, "beanName null");
        Assert.notNull(methodName, "methodName null");
        Assert.notNull(typeName, "typeName null");
        Assert.notNull(resourcePath, "resourcePath null");

        Class<?> type = ClassUtil.loadClass(typeName);
        Object bean = SpringUtil.getBean(beanName);
        Method method = ReflectUtil.getMethodByName(bean.getClass(), methodName);

        try (InputStream inputStream = resourceAccessor.openStream(null, resourcePath)) {
            List<Object> objectList = parse(inputStream, type);
            method.invoke(bean, objectList);
        } catch (Exception e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

    /**
     * 解析数据
     *
     * @param inputStream
     * @param type
     * @return
     */
    protected List<Object> parse(InputStream inputStream, Class<?> type) {
        Assert.notNull(inputStream, "inputStream null");
        Assert.notNull(type, "type null");
        List<Object> objectList = (List<Object>) MybatisPlusCsvUtil.readCSV(inputStream, type);

        return objectList;
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
