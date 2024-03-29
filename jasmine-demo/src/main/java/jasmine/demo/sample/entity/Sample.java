package jasmine.demo.sample.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.database.mybatisplus.annotation.I18n;
import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;
import jasmine.framework.database.mybatisplus.crypto.CryptoTypeHandler;

/**
 * @author mh.z
 */
@TableName(value = "demo_sample", autoResultMap = true)
public class Sample extends BaseI18nEntity {
    /** 代码 */
    @TableField("sample_code")
    private String sampleCode;

    /** 名称 */
    @I18n
    @TableField("sample_name")
    private String sampleName;

    /** 秘密信息 */
    @TableField(value = "secret_info", typeHandler = CryptoTypeHandler.class)
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
