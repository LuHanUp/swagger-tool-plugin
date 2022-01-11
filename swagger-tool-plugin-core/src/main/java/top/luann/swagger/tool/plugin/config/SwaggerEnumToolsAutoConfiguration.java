package top.luann.swagger.tool.plugin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.luann.swagger.tool.plugin.core.ShowDocContext;
import top.luann.swagger.tool.plugin.core.SwaggerEnumToolsModelPropertyBuilderPlugin;
import top.luann.swagger.tool.plugin.core.strategy.impl.ShowDocStrategyComposite;

/**
 * swagger-enum-tools的自动装载类
 *
 * @author luHan
 * @create 2021/12/13 16:54
 * @since 1.0.0
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger", value = "enable", havingValue = "true")
@ComponentScan(basePackages = {"top.luann.swagger.tool.plugin.core.strategy"})
public class SwaggerEnumToolsAutoConfiguration {
    @Bean
    public ShowDocContext showDocContext(ShowDocStrategyComposite showDocStrategyComposite) {
        return new ShowDocContext(showDocStrategyComposite);
    }

    @Bean
    public SwaggerEnumToolsModelPropertyBuilderPlugin swaggerEnumToolsModelPropertyBuilderPlugin(ShowDocContext showDocContext) {
        return new SwaggerEnumToolsModelPropertyBuilderPlugin(showDocContext);
    }

}
