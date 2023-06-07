# 添加依赖

```
<!-- 权限认证框架 -->
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter-security</artifactId>
    <scope>compile</scope>
</dependency>
```

# 使用配置

```
# 是否启用RBAC访问控制 (默认default不启用)
jasmine.security.authorization.strategy=rbac

# 自定义公开资源，默认是 "/static/**"
jasmine.security.publicLocations=/static/**

备注：更多配置查看 jasmine.autoconfigure.security.JasmineSecurityProperties。
```
