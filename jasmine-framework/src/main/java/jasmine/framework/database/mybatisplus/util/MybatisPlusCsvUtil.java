package jasmine.framework.database.mybatisplus.util;

import cn.hutool.core.lang.Assert;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author mh.z
 */
public class MybatisPlusCsvUtil {

    /**
     * 读取CSV
     *
     * @param inputStream
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> readCSV(InputStream inputStream, Class<T> type) {

        return readCSV(inputStream, type, Collections.emptyMap());
    }

    /**
     * 读取CSV
     *
     * @param inputStream
     * @param type
     * @param valueConverters
     * @param <T>
     * @return
     */
    public static <T> List<T> readCSV(InputStream inputStream, Class<T> type,
                                      Map<Class<?>, Function> valueConverters) {
        Assert.notNull(inputStream, "inputStream null");
        Assert.notNull(type, "type null");
        Assert.notNull(valueConverters, "valueConverters null");

        // 列名映射
        MybatisPlusColumnMapping columnMapping = new MybatisPlusColumnMapping(type);
        HeaderColumnNameTranslateMappingStrategy mappingStrategy = new HeaderColumnNameTranslateMappingStrategy();
        mappingStrategy.setColumnMapping(columnMapping);
        mappingStrategy.setType(type);

        // 读取CSV
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(reader);
        CsvToObject csvToObject = new CsvToObject(valueConverters);
        List<T> objectList = csvToObject.parse(mappingStrategy, csvReader);

        return objectList;
    }

}
