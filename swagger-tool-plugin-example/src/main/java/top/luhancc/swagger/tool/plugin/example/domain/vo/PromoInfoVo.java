package top.luhancc.swagger.tool.plugin.example.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.luann.swagger.tool.plugin.enums.ShowManyDoc;
import top.luhancc.swagger.tool.plugin.example.domain.rule.RedeemRule;
import top.luhancc.swagger.tool.plugin.example.domain.rule.SeckillRule;

/**
 * 活动信息vo类
 *
 * @author luHan
 * @create 2022/1/17 18:03
 * @since 1.0.0
 */
@Data
@ApiModel(value = "活动信息")
public class PromoInfoVo {

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动描述")
    private String desc;

    @ApiModelProperty(value = "活动具体的规则")
    @ShowManyDoc({SeckillRule.class, RedeemRule.class})
    private String rule;
}
