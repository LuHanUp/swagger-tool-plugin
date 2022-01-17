package top.luann.swagger.tool.plugin.enums;

import java.lang.annotation.*;

/**
 * 用于一个字段表示多个规则的显示文档注解类
 *
 * @author luHan
 * @create 2022/1/17 18:00
 * @since 1.0.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShowManyDoc {
    Class<?>[] value();
}
