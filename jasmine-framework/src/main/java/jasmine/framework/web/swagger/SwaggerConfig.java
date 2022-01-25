package jasmine.framework.web.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <p>
 * 配置Swagger生成API接口文档。
 * </p>
 */
@Configuration
public class SwaggerConfig {
    @Value("${app.swagger.version:}")
    private String apiVersion;

    @Value("${app.swagger.title:}")
    private String apiTitle;

    @Value("${app.swagger.base-package:}")
    private String apiPackage;

    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(apiTitle)
                .version(apiVersion)
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage(apiPackage))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

}
