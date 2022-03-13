package jasmine.demo.sample.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author mh.z
 */
@ApiModel(description = "参数DTO")
public class ParamsDTO {
    @ApiModelProperty("参数1")
    private String param1;

    @ApiModelProperty("参数2")
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
