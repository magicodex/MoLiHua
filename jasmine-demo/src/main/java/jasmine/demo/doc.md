## 代码风格

- 使用 UTF-8 编码。
- 使用 \n 换行符。
- 使用空格缩进。

## 目录结构

### module

```
|---- application
   |---- web
      |---- controller 控制器
      |---- conversion
      |---- dto
      |---- validation
|---- business 业务层
   |---- conversion
   |---- dto
   |---- service
   |---- domain 领域驱动对象
   |---- helper
|---- persistence 持久层
   |---- cond 条件对象
   |---- dao
   |---- mapper
   |---- model 数据实体
|---- constant
|---- enums
|---- exception
|---- util

可通过以下命令创建模块目录：
mkmod 模块名
```

### framework

```
|---- config
   |---- security 登录认证
|---- i18n
|---- impl
   |---- context
   |---- global
      |---- lock
      |---- transaction
   |---- support
      |---- consumer
      |---- publisher
|---- security
```

### 实用类

| 类名 | 描述 |
| :----: | :---- |
| CurrentSubject | 可通过该类获取当前用户信息 |
| RuntimeProvider | 提供根据类型获取对象的方法 |
| BusinessException | 业务异常，在业务校验不通过时可抛出该异常 |
| UnexpectedException | 不期望的异常，表示不应该出现的错误 |
| Q | 定义常用的工具方法 |
| I18N | 提供查找多语言的方法 |
| QCheckUtil | 可用于检查参数、返回值等 |
| QNewUtil | 用于创建指定类的对象 |
