package jasmine.framework.web.util.routing.testdependency;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class SampleUtils {

    /**
     * 加载样例
     *
     * @param path 文件的路径
     * @return 加载的样例
     */
    public static Collection<SampleGroup> loadSampleGroupsFromClasspath(String path) {
        if (path == null) {
            throw new IllegalArgumentException("path null");
        }

        Collection<SampleGroup> sampleGroups = null;
        InputStream inputStream = SampleUtils.class.getResourceAsStream(path);

        if (inputStream == null) {
            throw new RuntimeException("not found file '" + path + "' in classpath");
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<SampleGroup>> reference = new TypeReference<List<SampleGroup>>() {
                //
            };

            sampleGroups = mapper.readValue(inputStream, reference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sampleGroups;
    }

}
