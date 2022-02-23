package jasmine.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * <p>
 * 1. SpringBootApplication 声明扫描 jasmine 包路径下类。
 * 2. EnableAutoConfiguration 启用自动配置。
 * 3. MapperScan 声明扫描 Mapper 接口的包路径。
 * 4. EnableSwagger2WebMvc 启用文档注解。
 * </p>
 *
 * @author mh.z
 */
@Slf4j
@MapperScan("jasmine.**.mapper")
@EnableSwagger2WebMvc
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"jasmine.demo", "jasmine.autoconfigure", "jasmine.security"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);

        log.info("Server running...");
    }

}