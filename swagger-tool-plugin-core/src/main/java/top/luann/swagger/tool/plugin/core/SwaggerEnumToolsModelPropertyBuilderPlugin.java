package top.luann.swagger.tool.plugin.core;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import springfox.documentation.schema.Annotations;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import springfox.documentation.swagger.schema.ApiModelProperties;

import java.util.Optional;

/**
 * 对指定的数据和枚举的转换显示在Swagger文档上
 *
 * @author luHan
 * @create 2021/12/13 14:05
 * @since 1.0.0
 */
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1)
public class SwaggerEnumToolsModelPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {
    private final Logger log = LoggerFactory.getLogger(SwaggerEnumToolsModelPropertyBuilderPlugin.class);

    private final ShowDocContext showDocContext;

    public SwaggerEnumToolsModelPropertyBuilderPlugin(ShowDocContext showDocContext) {
        this.showDocContext = showDocContext;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        // 获取当前字段的类型
        Optional<BeanPropertyDefinition> beanPropertyDefinition = context.getBeanPropertyDefinition();
        if (beanPropertyDefinition.isPresent()) {
            final Class<?> fieldType = beanPropertyDefinition.get().getField().getRawType();
            // 为字段设置注释文档
            descForFields(context, fieldType);
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }

    /**
     * 为model字段生成描述
     */
    private void descForFields(ModelPropertyContext context, Class<?> fieldType) {
        Optional<ApiModelProperty> annotation = Optional.empty();
        if (context.getAnnotatedElement().isPresent()) {
            annotation = ApiModelProperties.findApiModePropertyAnnotation(context.getAnnotatedElement().get());
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = Annotations.findPropertyAnnotation(
                    context.getBeanPropertyDefinition().get(),
                    ApiModelProperty.class);
        }
        if (!annotation.isPresent()) {
            log.debug("api model property not has @ApiModelProperty annotation: property:{}", fieldType.getSimpleName());
            return;
        }
        // 获取@ApimodelProperty的value作为文档描述的前缀
        String description = annotation.get().value();

        // 将要显示在swagger页面上的属性描述信息
        String docDescInfo = showDocContext.showDocDescInfo(context, fieldType);
        if (docDescInfo != null) {
            description = description + docDescInfo;
        }
        // 将文档描述信息进行显示在swagger文档上
        context.getBuilder().description(description);
        context.getSpecificationBuilder().description(description);
    }
}
