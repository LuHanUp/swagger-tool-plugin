package top.luann.swagger.tool.plugin.core.strategy;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

/**
 * 显示文档描述信息的抽象策略接口
 *
 * @author luHan
 * @create 2021/12/13 17:22
 * @since 1.0.0
 */
public interface ShowDocStrategist {

    /**
     * 是否支持处理fieldType这个字段属性
     *
     * @param field 需要显示文档描述信息的字段上下文信息
     * @return
     */
    boolean support(AnnotatedField field);


    /**
     * 返回需要显示的文档描述信息
     *
     * @param context
     * @param fieldType
     * @return
     */
    String showDocInfo(ModelPropertyContext context, Class<?> fieldType);
}
