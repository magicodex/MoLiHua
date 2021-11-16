package jasmine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"jasmine"})
public class JasmineApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasmineApplication.class);
    }

}
