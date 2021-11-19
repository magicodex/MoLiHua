package jasmine.framework.impl.context;

import jasmine.common.annotation.DeclareI18N;
import jasmine.common.util.QCheckUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.lang.reflect.Field;
import java.util.Properties;

public class DeclareI18nScanUtil {

    public static Properties scan(String locationPattern) {
        QCheckUtil.notNull(locationPattern, "locationPattern null");
        Properties properties = new Properties();

        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(pathResolver);
        ClassLoader classLoader = DeclareI18nScanUtil.class.getClassLoader();

        try {
            Resource[] resources = pathResolver.getResources(locationPattern);

            for (Resource resource : resources) {
                MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                String className = classMetadata.getClassName();
                Class<?> clazz = classLoader.loadClass(className);

                doScan(properties, clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

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
                Object key = field.get(null);
                String value = annotation.value();

                properties.put(key, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
