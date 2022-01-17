package top.luann.swagger.tool.plugin.core.strategy.impl;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import top.luann.swagger.tool.plugin.core.strategy.ShowDocStrategist;
import top.luann.swagger.tool.plugin.enums.ShowManyDoc;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ShowManyDoc注解的显示文档描述信息的策略实现类
 *
 * <text>@ShowManyDoc</text>注解实现
 *
 * @author luHan
 * @create 2021/12/13 17:27
 * @since 1.0.0
 */
@Component
public class ShowManyDocStrategy implements ShowDocStrategist {
    private final Logger log = LoggerFactory.getLogger(ShowManyDocStrategy.class);

    private final Map<Class<?>, String> cache = new ConcurrentHashMap<>();

    @Override
    public boolean support(AnnotatedField field) {
        return field.getAnnotated().isAnnotationPresent(ShowManyDoc.class);
    }

    @Override
    public String showDocInfo(ModelPropertyContext context, Class<?> fieldType) {
        if (context.getBeanPropertyDefinition().isPresent()) {
            AnnotatedField annotatedField = context.getBeanPropertyDefinition().get().getField();
            return descByManyClass(annotatedField.getAnnotation(ShowManyDoc.class));
        }
        if (log.isDebugEnabled()) {
            log.debug("ModelPropertyContext not find BeanPropertyDefinition.context:{}", context);
        }
        return "";
    }


    /**
     * 生成ShowManyDoc注解的描述
     *
     * @param showManyDoc 枚举类
     * @return
     */
    private String descByManyClass(ShowManyDoc showManyDoc) {
        Class<?>[] classes = showManyDoc.value();
        StringBuilder sb = new StringBuilder();
        for (Class<?> clazz : classes) {
            // 解析类上是否带有@ApiModel注解,如果没有,就使用类名代替
            String modelName = "";
            if (clazz.isAnnotationPresent(ApiModel.class)) {
                modelName = clazz.getAnnotation(ApiModel.class).value();
            }
            modelName = StringUtils.isEmpty(modelName) ? clazz.getSimpleName() : modelName;
            sb.append(modelName).append(":");

            StringBuilder fieldDescSb = new StringBuilder("{");

            // 获取类中所有的属性字段
            Set<Field> fieldSet = Stream.of(clazz.getDeclaredFields()).collect(Collectors.toSet());
            for (Field field : fieldSet) {
                String fieldName = field.getName();
                String fieldDesc = fieldName;
                if (field.isAnnotationPresent(ApiModelProperty.class)) {
                    ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                    fieldDesc = apiModelProperty.value();
                }
                fieldDescSb.append("\"").append(fieldName).append("\"").append(":").append("\"").append(fieldDesc).append("\"").append(",");
            }
            fieldDescSb.deleteCharAt(fieldDescSb.length() - 1);
            fieldDescSb.append("}");

            sb.append(fieldDescSb);
        }
        return sb.toString();
    }
}
