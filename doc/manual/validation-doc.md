# 示例

```
// 以下是使用 SpringMVC 校验参数
public ResponseEntity<WebResult<Sample>> sample(..., Errors errors, ...) {
    ValidationHelper helper = ValidationHelper.create(errors);
    // 参数 param1 不能为空
    helper.field("param1").rejectIfEmpty();
    
    if (helper.hasErrors()) {
        return helper.toEntity();
    }
    ...
}
```
