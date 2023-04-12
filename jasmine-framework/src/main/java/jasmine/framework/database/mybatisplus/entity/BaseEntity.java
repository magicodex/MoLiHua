package jasmine.framework.database.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;

import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
public class BaseEntity {

    /** 唯一标识 */
    @TableId("id")
    private Long id;

    /** 创建日期 */
    @TableField(value = "created_date", fill = FieldFill.INSERT)
    private ZonedDateTime createdDate;

    /** 创建人ID */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private Long createdBy;

    /** 最后更新日期 */
    @TableField(value = "last_updated_date", fill = FieldFill.INSERT_UPDATE)
    private ZonedDateTime lastUpdatedDate;

    /** 最后更新人ID */
    @TableField(value = "last_updated_by", fill = FieldFill.INSERT_UPDATE)
    private Long lastUpdatedBy;

    /** 版本号 */
    @Version
    @TableField(value = "version_number", fill = FieldFill.INSERT)
    private Integer versionNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(ZonedDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

}
