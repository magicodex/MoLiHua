package jasmine.framework.web.util.routing.testdependency;

import java.util.Map;

/**
 * @author mh.z
 */
public class Sample {
    private String key;
    private Map<String, String> values;

    public Sample() {

    }

    public Sample(String key, Map<String, String> values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

}
