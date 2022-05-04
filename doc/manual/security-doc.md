# 依赖

```
<!-- 权限认证框架 -->
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter-security</artifactId>
    <version>最新版本</version>
</dependency>
```

# 配置

```
# 是否启用RBAC访问控制 (默认default不启用)
jasmine.security.authorization.strategy=rbac

# 自定义公开资源，默认是 "/static/**"
jasmine.security.publicLocations=/static/**
```
