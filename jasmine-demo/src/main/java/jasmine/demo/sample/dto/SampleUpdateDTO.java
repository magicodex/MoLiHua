package jasmine.demo.sample.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mh.z
 */
@ApiModel(description = "sample更新DTO")
public class SampleUpdateDTO {
    @NotNull
    @ApiModelProperty("记录ID")
    private String id;

    @NotEmpty
    @ApiModelProperty("代码")
    private String sampleCode;

    @NotEmpty
    @ApiModelProperty("名称")
    private String sampleName;

    @ApiModelProperty("秘密信息")
    private String secretInfo;

    @NotEmpty
    @ApiModelProperty("版本号")
    private Integer versionNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

}
