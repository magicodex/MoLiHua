package jasmine.security.rbac.model;

/**
 * <p>
 * 菜单。
 * </p>
 *
 * @author mh.z
 */
public class SecurityMenu {
    private Long id;
    /** 模板ID */
    private Long templateId;
    /** 父菜单ID */
    private Long parentId;

    /** 菜单代码 */
    private String menuCode;
    /** 菜单名称 */
    private String menuName;
    /** 菜单序号 */
    private Integer menuOrder;
    /** 页面 */
    private String targetPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }

}
