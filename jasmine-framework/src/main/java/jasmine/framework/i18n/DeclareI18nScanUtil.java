package jasmine.framework.i18n;


import jasmine.core.i18n.DeclareI18N;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QErrorUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * <p>
 * 扫描多语言常量。
 * </p>
 *
 * @author mh.z
 */
public class DeclareI18nScanUtil {

    /**
     * 扫描多语言常量
     *
     * @param locationPattern
     * @return
     */
    public static Properties scan(String locationPattern) {
        QCheckUtil.notNull(locationPattern, "locationPattern null");
        Properties properties = new Properties();

        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(pathResolver);
        ClassLoader classLoader = DeclareI18nScanUtil.class.getClassLoader();

        try {
            // 获取多语言常量类信息
            Resource[] resources = pathResolver.getResources(locationPattern);

            for (Resource resource : resources) {
                MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                String className = classMetadata.getClassName();
                // 加载多语言常量类
                Class<?> clazz = classLoader.loadClass(className);

                // 扫描多语言常量类获取多语言信息
                doScan(properties, clazz);
            }
        } catch (Exception e) {
            throw QErrorUtil.sneakyError(e);
        }

        return properties;
    }

    /**
     * 扫描多语言常量
     *
     * @param properties
     * @param clazz
     */
    protected static void doScan(Properties properties, Class<?> clazz) {
        QCheckUtil.notNull(properties, "properties null");
        QCheckUtil.notNull(clazz, "clazz null");

        if (!clazz.isInterface()) {
            return;
        }

        Field[] fields = clazz.getFields();
        if (fields.length == 0) {
            return;
        }

        for (Field field : fields) {
            DeclareI18N annotation = field.getAnnotation(DeclareI18N.class);

            if (annotation == null) {
                continue;
            }

            try {
                // 获取多语言信息
                String key = (String) field.get(null);
                String value = annotation.value();

                if (key != null && key.startsWith("$")) {
                    key = key.substring(1);
                }

                properties.put(key, value);
            } catch (IllegalAccessException e) {
                throw QErrorUtil.sneakyError(e);
            }
        }
    }

}
