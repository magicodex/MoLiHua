package jasmine.demo.journal.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mh.z
 */
@NoArgsConstructor
@Data
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

}
