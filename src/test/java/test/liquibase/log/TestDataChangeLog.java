package test.liquibase.log;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.ZonedDateTime;

/**
 * <p>
 * 测试数据变更日志。
 * </p>
 *
 * @author mh.z
 */
@TableName("test_data_change_log")
public class TestDataChangeLog {

    @TableId
    private Long id;

    /** 资源路径 */
    @TableField("resource_path")
    private String resourcePath;

    /** 资源名 */
    @TableField("resource_name")
    private String resourceName;

    /** 记录ID */
    @TableField("record_id")
    private Long recordId;

    /** 创建日期 */
    @TableField("created_date")
    private ZonedDateTime createdDate;

    /** 最后更新日期 */
    @TableField("last_updated_date")
    private ZonedDateTime lastUpdatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(ZonedDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

}
