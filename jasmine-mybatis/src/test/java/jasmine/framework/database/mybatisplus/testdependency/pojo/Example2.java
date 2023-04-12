package jasmine.framework.database.mybatisplus.testdependency.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
public class Example2 extends Example1 {
    @TableId
    private Long key;

    @TableField("attribute_4")
    private Byte attribute4;

    @TableField("attribute_5")
    private ZonedDateTime attribute5;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

}
