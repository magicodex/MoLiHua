package jasmine.framework.web.util.routing.testdependency;

import cn.hutool.core.io.FileUtil;
import jasmine.framework.common.util.ErrorUtil;
import jasmine.framework.common.util.JsonUtil;

import java.util.Collection;

/**
 * @author mh.z
 */
public class SampleUtil {

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

        Collection<SampleGroup> sampleGroups;

        try {
            String json = FileUtil.readUtf8String(path);
            sampleGroups = JsonUtil.fromArray(json, SampleGroup.class);
        } catch (Exception e) {
            throw ErrorUtil.sneakyError(e);
        }

        return sampleGroups;
    }

}
