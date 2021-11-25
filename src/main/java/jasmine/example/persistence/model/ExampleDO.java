package jasmine.example.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author mh.z
 */
@TableName("demo_example")
public class ExampleDO {
    private Long id;
    private String exampleCode;
    private String exampleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExampleCode() {
        return exampleCode;
    }

    public void setExampleCode(String exampleCode) {
        this.exampleCode = exampleCode;
    }

    public String getExampleName() {
        return exampleName;
    }

    public void setExampleName(String exampleName) {
        this.exampleName = exampleName;
    }

}
