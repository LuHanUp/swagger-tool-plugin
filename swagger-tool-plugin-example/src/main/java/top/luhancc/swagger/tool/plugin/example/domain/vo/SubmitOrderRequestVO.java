package top.luhancc.swagger.tool.plugin.example.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.luann.swagger.tool.plugin.enums.ShowEnumDoc;
import top.luhancc.swagger.tool.plugin.example.domain.enums.PayTypeEnum;

/**
 * @author luHan
 * @create 2022/1/11 14:22
 * @since 1.0.0
 */
@Data
@ApiModel("ShowEnumRequestVO")
public class SubmitOrderRequestVO {

    @ApiModelProperty(value = "支付类型")
    @ShowEnumDoc(enumClass = PayTypeEnum.class)
    private Integer payType;

    @ApiModelProperty(value = "商品名称")
    private String productName;
}
