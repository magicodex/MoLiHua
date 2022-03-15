package jasmine.demo.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mh.z
 */
@Schema(title = "sample更新DTO")
public class SampleUpdateDTO {
    @NotNull
    @Schema(title = "记录ID")
    private String id;

    @NotEmpty
    @Schema(title = "代码")
    private String sampleCode;

    @NotEmpty
    @Schema(title = "名称")
    private String sampleName;

    @Schema(title = "秘密信息")
    private String secretInfo;

    @NotNull
    @Schema(title = "版本号")
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
