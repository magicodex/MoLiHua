package jasmine.demo.sample.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @author mh.z
 */
@ApiModel(description = "sample创建DTO")
public class SampleCreateDTO {

    @NotEmpty
    @ApiModelProperty("代码")
    private String sampleCode;

    @NotEmpty
    @ApiModelProperty("名称")
    private String sampleName;

    @ApiModelProperty("秘密信息")
    private String secretInfo;

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSecretInfo() {
        return secretInfo;
    }

    public void setSecretInfo(String secretInfo) {
        this.secretInfo = secretInfo;
    }

}
