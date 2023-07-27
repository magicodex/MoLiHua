## 测试常量

TestConstants 定义了常用的测试常量，比如

| 字段                     | 值      | 说明         |
|------------------------|--------|------------|
| TEST_TENANT_ID_UNKNOWN | -1     | 测试未知租户ID   |
| TEST_TENANT_ID_1       | 1      | 测试租户1      |
| TEST_TENANT_ID_2       | 2      | 测试租户2      |
| TEST_USER_ID_UNKNOWN   | -1     | 测试未知用户ID   |
| TEST_USER_ID_1         | 100001 | 测试用户1      |
| TEST_USER_ID_2         | 100002 | 测试用户2      |
| TEST_USER_ID_666666    | 666666 | 测试用户666666 |

# 添加依赖

```
<dependency>
    <groupId>com.magicodex</groupId>
    <artifactId>jasmine-framework-test</artifactId>
    <scope>test</scope>
</dependency>
```

# 使用配置

复制以下文件到项目 src/test/resources 目录下并去掉 .bak 后缀：
* jasmine-framework-test/src/main/resources/test-config.properties.bak
* jasmine-framework-test/src/main/resources/test-context.xml.bak
* jasmine-framework-test/src/main/resources/test-data.xml.bak

# 代码示例
```
@RunWith(SpringRunner.class)
public class UserServiceTest extends AppTestContext {
    @Autowired
    private UserService userService;

    @Test
    public void testLoadUserByUsername() {
        UserDetails actual = userService.loadUserByUsername("test");

        Assert.assertNotNull(actual);
    }

}

```