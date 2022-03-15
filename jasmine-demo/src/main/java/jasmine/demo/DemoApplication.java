package jasmine.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 1. SpringBootApplication 声明扫描 jasmine 包路径下类。
 * 2. EnableAutoConfiguration 启用自动配置。
 * 3. MapperScan 声明扫描 Mapper 接口的包路径。
 * 4. EnableOpenApi 启用文档注解。
 * </p>
 *
 * @author mh.z
 */
@MapperScan("jasmine.demo.**.mapper")
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"jasmine.demo"})
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);

        logger.info("Server running...");
    }

}