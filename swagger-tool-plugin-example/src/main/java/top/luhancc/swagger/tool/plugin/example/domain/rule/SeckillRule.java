package top.luhancc.swagger.tool.plugin.example.domain.rule;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 秒杀活动规则类
 *
 * @author luHan
 * @create 2022/1/17 18:06
 * @since 1.0.0
 */
@Data
public class SeckillRule {
    @ApiModelProperty(value = "秒杀价格")
    private Integer price;

    @ApiModelProperty(value = "商品原价,达到的价格才能参加秒杀")
    private Long originPrice;
}
