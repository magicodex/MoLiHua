
# 示例
```
// 以下是示例 SpringMVC 校验数据
public XXXX XXXX(..., Errors errors, ...) {
    ValidationHelper helper = ValidationHelper.create(errors);
    helper.field("param1", "").rejectIfEmpty();
}
```
