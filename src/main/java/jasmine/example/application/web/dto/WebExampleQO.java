package jasmine.example.application.web.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author mh.z
 */
public class WebExampleQO {
    @NotBlank
    private String exampleName;

    public String getExampleName() {
        return exampleName;
    }

    public void setExampleName(String exampleName) {
        this.exampleName = exampleName;
    }

}
