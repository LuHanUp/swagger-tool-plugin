package top.luhancc.swagger.tool.plugin.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author luHan
 * @create 2022/1/11 14:30
 * @since 1.0.0
 */
@EnableSwagger2
@Configuration
@ConditionalOnProperty(prefix = "swagger", value = "enable", havingValue = "true")
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("top.luhancc.swagger.tool.plugin.example.controller")).paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("swagger-tool-plugin示例工程")
                // 创建人信息
                .contact(new Contact("luHan", "", "765478939@qq.com"))
                // 版本号
                .version("1.0.0")
                // 描述
                .description("swagger-tool-plugin示例工程 1. 显示使用@ShowEnumDoc来显示枚举类型的文档描述")
                .build();
    }
}
