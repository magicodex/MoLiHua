package jasmine.test.testdependency;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author mh.z
 */
public class Example2 extends Example1 {
    @TableId
    private Long key;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

}
