package test.liquibase.util;

import cn.hutool.core.lang.Assert;
import liquibase.util.csv.CSVReader;
import liquibase.util.csv.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import test.liquibase.csv.CsvToObject;
import test.liquibase.csv.MybatisPlusColumnMapping;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvUtil {

    public static <T> List<T> readCSV(InputStream inputStream, Class<T> type) {
        Assert.notNull(inputStream, "inputStream null");
        Assert.notNull(type, "type null");

        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(reader);
        MybatisPlusColumnMapping columnMapping = new MybatisPlusColumnMapping(type);
        HeaderColumnNameTranslateMappingStrategy mappingStrategy = new HeaderColumnNameTranslateMappingStrategy();

        CsvToObject csvToObject = new CsvToObject();
        List<T> objectList = csvToObject.parse(mappingStrategy, csvReader);

        return objectList;
    }

}
