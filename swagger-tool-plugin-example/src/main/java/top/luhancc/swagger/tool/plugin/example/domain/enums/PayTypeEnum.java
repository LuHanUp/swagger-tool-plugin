package top.luhancc.swagger.tool.plugin.example.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luHan
 * @create 2022/1/11 14:25
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {
    WECHAT(1, "微信支付"),
    ALIPAY(2, "支付宝支付"),
    ;

    private Integer code;
    private String desc;
}
