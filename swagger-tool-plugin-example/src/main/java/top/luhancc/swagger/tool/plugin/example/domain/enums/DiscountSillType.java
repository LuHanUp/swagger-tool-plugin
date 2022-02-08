package top.luhancc.swagger.tool.plugin.example.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠门槛类型
 *
 * @author luHan
 * @create 2021/2/25 18:00
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DiscountSillType {
    FULL_YUAN(1, "满n元"),
    FULL_PIECES(2, "满n件"),
    ;

    private Integer code;
    private String desc;
}
