package jasmine.framework.web.util.routing.testdependency;

/**
 * @author mh.z
 */
public class SampleGroup {
    private String path;
    private Sample[] samples;

    public SampleGroup() {

    }

    public SampleGroup(String path, Sample[] samples) {
        this.path = path;
        this.samples = samples;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Sample[] getSamples() {
        return samples;
    }

    public void setSamples(Sample[] samples) {
        this.samples = samples;
    }

}
