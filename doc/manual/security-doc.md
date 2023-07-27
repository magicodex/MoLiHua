# 添加依赖

```
<!-- 权限认证框架 -->
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-spring-boot-starter-security</artifactId>
    <scope>compile</scope>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>org.springframework.security.oauth</groupId>
    <artifactId>spring-security-oauth2</artifactId>
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

# 实现接口
* UserSubjectDetailsService: 用户信息查询接口
* ClientSubjectDetailsService: 用户信息查询接口