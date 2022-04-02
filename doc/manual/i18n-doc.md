# 示例

```
// 定义多语言常量
public interface XXXXMessages {
  @DeclareI18N("你好，世界！")
  String HELLO_WORLD = "helloWorld";
}

// 获取多语言信息
String message = QI18nUtil.getMessage(XXXXMessages.HELLO_WORLD);
```
