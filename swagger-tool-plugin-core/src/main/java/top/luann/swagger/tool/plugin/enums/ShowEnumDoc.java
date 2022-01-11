package top.luann.swagger.tool.plugin.enums;

import java.lang.annotation.*;

/**
 * 显示enum类型的doc
 *
 * @author luHan
 * @create 2021/12/13 17:02
 * @since 1.0.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShowEnumDoc {

    /**
     * 对应的枚举类
     *
     * @return
     */
    Class<?> enumClass();

    /**
     * code值的字段名称
     *
     * @return
     */
    String valueName() default "code";

    /**
     * code值对应的描述
     *
     * @return
     */
    String descName() default "desc";
}
