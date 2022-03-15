package jasmine.demo.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

/**
 * @author mh.z
 */
@Schema(title = "sample创建DTO")
public class SampleCreateDTO {

    @NotEmpty
    @Schema(title = "代码")
    private String sampleCode;

    @NotEmpty
    @Schema(title = "名称")
    private String sampleName;

    @Schema(title = "秘密信息")
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
