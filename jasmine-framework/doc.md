### 目录划分

| 名称         | 说明           |
|------------|--------------|
| common     | 公共模块         |
| context    | 上下文          |
| i18n       | 支持多语言        |
| web        | 扩展 WEB 相关的功能 |
| cache      | 缓存           |
| concurrent | 扩展并发相关的功能    |
| lock       | 包括分布式锁       |
| job        | 任务调度         |
| database   | 数据库          |
| mq         | 消息队列         |


### 上下文

| 名称 | 说明 |
| --- | --- |
| CurrentSubject | 获取当前用户的有关信息 |
| SubjectProvider | 定义获取用户有关信息的接口 |
| RuntimeProvider | 提供获取对象的方法 |
| InitSupport | 可以解决 IOC 循环依赖问题 |

### 异常类

| 名称 | 说明 |
| --- | --- |
| ApplicationException | 应用异常，支持多语言 |
| BusinessException | 业务异常，在业务校验不通过时可抛出该异常 |
| UnexpectedException | 不期望异常，表示不应该出现的错误 |
| InvalidParameterException | 无效参数异常，表示参数的值无效 |
| InvalidPropertyException | 无效属性异常，表示属性的值无效 |
| DataNotFoundException | 未找到数据异常，表示未找到数据 |
| InvalidDataException | 无效数据异常，表示数据有错误 |

备注："$"开头的错误信息是是多语言key，比如以下示例

```
throw new ApplicationException("hello");
=> 错误信息是 "hello"

throw new ApplicationException("$hello", null);
=> 错误信息是多语言key "hello" 对应的多语言信息
```

### 多语言

| 名称 | 说明 |
| --- | --- |
| DeclareI18N | 多语言注解 |
| LocaleMessageProvider | 获取多语言的接口 |

### 工具类

| 名称 | 说明 |
| --- | --- |
| CheckUtil | 可用于检查参数、返回值等 |
| NewUtil | 提供常见类的创建方法 |
| MapperUtil | 映射对象的工具类 |
| StringUtil | 字符串工具类 |
| DateUtil | 日期工具类 |
| CollectionUtil | 集合工具类 |
| CollUtil | 继承QCollectionUtil，缩短集合工具类的类名 |
| ObjectUtil | 对象工具类，提供转换类型等方法 |
| JsonUtil | JSON工具类 |
| I18nUtil | 多语言工具类 |
| SpringUtil | Spring工具类 |
| ErrorUtil | 提供异常类相关处理方法 |
| ... | ... |

## 示例

### 解决循环依赖问题的示例

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
