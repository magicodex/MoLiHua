package jasmine.security.rbac.model;

/**
 * <p>
 * 关联菜单和功能。
 * </p>
 *
 * @author mh.z
 */
public class SecurityMenuFunction {
    private Long id;
    /** 菜单ID */
    private Long menuId;
    /** 功能ID */
    private Long functionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

}