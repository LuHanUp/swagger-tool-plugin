package top.luann.swagger.tool.plugin.core.strategy.impl;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import top.luann.swagger.tool.plugin.core.strategy.ShowDocStrategist;
import top.luann.swagger.tool.plugin.enums.ShowEnumDoc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * ShowEnumDoc注解的显示文档描述信息的策略实现类
 *
 * <text>@ShowEnumDoc</text>注解实现
 *
 * @author luHan
 * @create 2021/12/13 17:27
 * @since 1.0.0
 */
@Component
public class ShowEnumDocStrategy implements ShowDocStrategist {
    private final Logger log = LoggerFactory.getLogger(ShowEnumDocStrategy.class);

    private final Map<Object, String> enumDescCache = new ConcurrentHashMap<>();

    @Override
    public boolean support(AnnotatedField field) {
        return field.getAnnotated().isAnnotationPresent(ShowEnumDoc.class);
    }

    @Override
    public String showDocInfo(ModelPropertyContext context, Class<?> fieldType) {
        AnnotatedField annotatedField = context.getBeanPropertyDefinition().get().getField();
        return descBySwaggerDisplayEnum(annotatedField.getAnnotation(ShowEnumDoc.class));
    }


    /**
     * 生成ShowEnumDoc注解的描述
     *
     * @param showEnumDoc 枚举类
     * @return
     */
    private String descBySwaggerDisplayEnum(ShowEnumDoc showEnumDoc) {
        Class<?> enumClass = showEnumDoc.enumClass();
        String descFieldName = showEnumDoc.descName();
        String codeFieldName = showEnumDoc.valueName();

        Object[] subItemRecords = null;
        if (Enum.class.isAssignableFrom(enumClass)) {
            subItemRecords = enumClass.getEnumConstants();
        }
        if (null == subItemRecords) {
            return "";
        }
        final List<String> displayValues = Arrays.stream(subItemRecords)
                .filter(Objects::nonNull)
                .map(o -> {
                    if (enumDescCache.containsKey(o)) {
                        return enumDescCache.get(o);
                    }
                    String description;
                    try {
                        Object value = FieldUtils.readDeclaredField(o, codeFieldName, true);
                        Object desc = FieldUtils.readDeclaredField(o, descFieldName, true);
                        description = value + ":" + desc;
                    } catch (IllegalAccessException e) {
                        log.warn("获取枚举属性值失败:", e);
                        description = o.toString();
                    }
                    enumDescCache.put(o, description);
                    return description;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(displayValues)) {
            return "";
        }
        return "(" + String.join(" ", displayValues) + ")";
    }
}
