package jasmine.framework.web.util.routing;

/**
 * @author mh.z
 */
public class PathInfo {
    /** 路径表达式 */
    private String expression;
    /** 路径对应的值 */
    private Object bindingValue;
    /** 路径参数名，元素不是null则是路径参数名否则不是路径参数 */
    private String[] pathParameterNames;

    public PathInfo(String expression, Object bindingValue, String[] pathParameterNames) {
        this.expression = expression;
        this.bindingValue = bindingValue;
        this.pathParameterNames = pathParameterNames;
    }

    public String getExpression() {
        return expression;
    }

    public Object getBindingValue() {
        return bindingValue;
    }

    public String[] getPathParameterNames() {
        return pathParameterNames;
    }

}
