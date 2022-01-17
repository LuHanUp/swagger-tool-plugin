package top.luhancc.swagger.tool.plugin.example.domain.rule;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.luann.swagger.tool.plugin.enums.ShowEnumDoc;

import java.util.List;

/**
 * 换购活动规则
 *
 * @author luHan
 * @create 2022/1/17 18:08
 * @since 1.0.0
 */
@Data
public class RedeemRule {

    /**
     * 为活动时间内周一到周日，如果只勾选部分日子，那么将在活动时间内部分日子有效；
     * 例如活动时间1月1号0点-1月30号0点；适用时间：周五；
     * 那么1月整月每周五0点换购活动开始；
     * 例如活动时间1月1号10点-2月1号0点.适用时间周一至周日全选，那么1月1号10点开始活动直至2月1号0点结束；
     */
    @ApiModelProperty(value = "适用时间: MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY")
    private List<String> applyTimes;

    /**
     * 权益次数
     */
    @ApiModelProperty(value = "权益次数")
    private Integer equityCount;

    /**
     * 自动循环叠加。例如满100换购2，满200换购4。除门槛和优惠内容翻倍之外，单商品的订单限购数量也会翻倍；
     */
    @ApiModelProperty(value = "优惠循环")
    private Boolean discountCycle;

    /**
     * 优惠门槛类型
     */
    @ApiModelProperty(value = "优惠门槛类型")
    @ShowEnumDoc(enumClass = DiscountSillType.class)
    private Integer discountSillType;

    /**
     * 优惠门槛值
     */
    @ApiModelProperty(value = "优惠门槛值 单位分和件")
    private String sillValue;

    /**
     * 换购任选值
     */
    @ApiModelProperty(value = "换购任选redeemOptional件")
    private Integer redeemOptional;
}
