### 上下文

| 名称 | 说明 |
| --- | --- |
| CurrentSubject | 获取当前用户的有关信息 |
| RuntimeProvider | 提供获取对象的方法 |

#### 解决循环依赖问题
```
@Component
public class Bean1 {
  private Bean2 bean2;
  
  public Bean1(Bean2 bean2) {
    this.bean2 = bean2;
  }
  
}

@Component
public class Bean2 implement InitSupport {
  private Bean1 bean1;
  
  @Override
  public void init(RuntimeProvider provider) {
    this.bean1 = provider.getByType(Bean1.class);
  }
  
}

```

### 异常类

| 名称 | 说明 |
| --- | --- |
| ApplicationException | 应用异常，支持多语言 |
| BusinessException | 业务异常，在业务校验不通过时可抛出该异常 |
| UnexpectedException | 期望的异常，表示不应该出现的错误 |
| InvalidParameterException | 无效参数的异常，表示参数的值无效 |
| InvalidPropertyException | 无效属性的异常，表示属性的值无效 |
| DataNotFoundException | 未找到数据的异常，表示未找到数据 |
| InvalidDataException | 无效数据，表示数据有错误 |

### 工具类

| 名称 | 说明 |
| --- | --- |
| QCheckUtil | 可用于检查参数、返回值等 |
| QNewUtil | 提供常见类的创建方法 |
| QMapperUtil | 映射对象工具类 |
| QStringUtil | 字符串工具类 |
| QDateUtil | 日期工具类 |
| QCollectionUtil | 集合工具类 |
| QObjectUtil | 对象工具类，提供转换类型等方法 |
| QJsonUtil | JSON工具类 |
| QI18nUtil | 多语言工具类 |
| QSpringUtil | Spring工具类 |
| QErrorUtil | 提供异常类相关处理方法 |

#### 使用多语言

```
// 定义多语言常量
public interface XXXXMessages {
  @DeclareI18N("你好，世界！")
  String HELLO_WORLD = "helloWorld";
}

// 获取多语言信息
String message = QI18nUtil.getMessage(XXXXMessages.HELLO_WORLD);
```