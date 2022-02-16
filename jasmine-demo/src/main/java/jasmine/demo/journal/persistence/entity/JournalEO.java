package jasmine.demo.journal.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
@TableName("jnl_journal")
public class JournalEO {
    @TableId
    private Long id;

    /** 用户ID */
    @TableField("user_id")
    private Long userId;

    /** 日记标题 */
    @TableField("journal_title")
    private String journalTitle;

    /** 日记内容 */
    @TableField("journal_content")
    private String journalContent;

    /** 创建日期 */
    @TableField("created_date")
    private ZonedDateTime createdDate;

    /** 最后更新日期 */
    @TableField("last_updated_date")
    private ZonedDateTime lastUpdatedDate;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalContent() {
        return journalContent;
    }

    public void setJournalContent(String journalContent) {
        this.journalContent = journalContent;
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

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
