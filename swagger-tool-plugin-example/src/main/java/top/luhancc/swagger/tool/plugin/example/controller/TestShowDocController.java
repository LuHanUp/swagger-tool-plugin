package top.luhancc.swagger.tool.plugin.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.luhancc.swagger.tool.plugin.example.domain.vo.PromoInfoVo;
import top.luhancc.swagger.tool.plugin.example.domain.vo.SubmitOrderRequestVO;

/**
 * 测试显示枚举类型swagger文档描述的控制器
 *
 * @author luHan
 * @create 2022/1/11 14:21
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/")
@Api(tags = "演示文档显示相关的api")
public class TestShowDocController {

    @RequestMapping(value = "showEnumDoc", method = RequestMethod.POST)
    @ApiOperation("显示枚举类型的文档描述")
    public String showEnumDoc(@RequestBody SubmitOrderRequestVO submitOrderRequestVO) {
        // 只是为了显示枚举类型的描述,故不做业务相关的处理
        return "success";
    }

    @RequestMapping(value = "showRuleDoc", method = RequestMethod.POST)
    @ApiOperation("显示单属性对应多个model类的文档描述")
    public String showRuleDoc(@RequestBody PromoInfoVo promoInfoVo) {
        // 只是为了显示枚举类型的描述,故不做业务相关的处理
        return "success";
    }
}
