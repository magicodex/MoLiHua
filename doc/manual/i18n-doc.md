# 配置

```
# 多语言资源路径，默认 message/messages
jasmine.i18n.message.resourcePrefix=

# 多语言资源编码，默认 UTF-8
jasmine.i18n.message.encoding=

# 多语言常量路径，默认 classpath*:/**/constant/*MessageConstants.class
jasmine.i18n.message.constantPattern=

备注：更多配置查看 jasmine.autoconfigure.framework.support.LocaleMessageProperties。
```

# 示例

```
// 定义多语言常量
public interface XxxxMessages {
  @DeclareI18N("你好，世界！")
  String HELLO_WORLD = "helloWorld";
}

// 获取多语言信息
String message = QI18nUtil.getMessage(XxxxMessages.HELLO_WORLD);
```
