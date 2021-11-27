package test.util;

import cn.hutool.core.lang.Assert;
import liquibase.util.csv.CSVReader;
import liquibase.util.csv.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import test.csv.CsvToObject;
import test.csv.MybatisPlusColumnMapping;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author mh.z
 */
public class StreamUtil {

    /**
     * 读取CSV
     *
     * @param inputStream
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> readCSV(InputStream inputStream, Class<T> type) {
        Assert.notNull(inputStream, "inputStream null");
        Assert.notNull(type, "type null");

        MybatisPlusColumnMapping columnMapping = new MybatisPlusColumnMapping(type);
        HeaderColumnNameTranslateMappingStrategy mappingStrategy = new HeaderColumnNameTranslateMappingStrategy();
        mappingStrategy.setColumnMapping(columnMapping);
        mappingStrategy.setType(type);

        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(reader);
        CsvToObject csvToObject = new CsvToObject();
        List<T> objectList = csvToObject.parse(mappingStrategy, csvReader);

        return objectList;
    }

}