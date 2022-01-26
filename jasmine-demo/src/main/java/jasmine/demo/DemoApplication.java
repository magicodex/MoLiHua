package jasmine.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * 1. SpringBootApplication 声明扫描 jasmine 包路径下类。
 * 2. EnableAutoConfiguration 启用自动配置。
 * 3. MapperScan 声明扫描 Mapper 接口的包路径。
 * 4. EnableSwagger2 启用文档注解。
 * </p>
 *
 * @author mh.z
 */
@MapperScan("jasmine.**.mapper")
@EnableSwagger2
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"jasmine"})
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);

        logger.info("Server running...");
    }

}