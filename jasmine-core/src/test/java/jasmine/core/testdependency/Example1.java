package jasmine.core.testdependency;

/**
 * @author mh.z
 */
public class Example1 {
    private String string1;
    private Integer integer1;
    private Boolean boolean1;

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Integer getInteger1() {
        return integer1;
    }

    public void setInteger1(Integer integer1) {
        this.integer1 = integer1;
    }

    public Boolean getBoolean1() {
        return boolean1;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    /**
     * 创建对象
     *
     * @param string1
     * @param integer1
     * @param boolean1
     * @return
     */
    public static Example1 create(String string1, Integer integer1, Boolean boolean1) {
        Example1 example = new Example1();

        example.setString1(string1);
        example.setInteger1(integer1);
        example.setBoolean1(boolean1);

        return example;
    }


}
