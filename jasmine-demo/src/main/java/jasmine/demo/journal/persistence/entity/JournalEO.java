package jasmine.demo.journal.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.entity.BaseEntity;

/**
 * @author mh.z
 */
@TableName("jnl_journal")
public class JournalEO extends BaseEntity {

    /** 用户ID */
    @TableField("user_id")
    private Long userId;

    /** 日记标题 */
    @TableField("journal_title")
    private String journalTitle;

    /** 日记内容 */
    @TableField("journal_content")
    private String journalContent;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

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

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
