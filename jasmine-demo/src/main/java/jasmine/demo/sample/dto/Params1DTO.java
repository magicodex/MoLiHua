package jasmine.demo.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author mh.z
 */
@Schema(title = "参数DTO")
public class Params1DTO {
    @Schema(title = "参数1")
    private String param1;

    @Schema(title = "参数2")
    private String param2;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
