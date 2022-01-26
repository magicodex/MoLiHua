package jasmine.demo.example.application.web.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author mh.z
 */
public class WebExampleQueryDTO {
    @NotBlank
    private String exampleName;

    public String getExampleName() {
        return exampleName;
    }

    public void setExampleName(String exampleName) {
        this.exampleName = exampleName;
    }

}
