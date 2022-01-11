package top.luann.swagger.tool.plugin.core;

import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import top.luann.swagger.tool.plugin.core.strategy.impl.ShowDocStrategyComposite;

/**
 * 显示文档描述信息的上下文对象
 *
 * @author luHan
 * @create 2021/12/13 17:07
 * @since 1.0.0
 */
public class ShowDocContext {
    private final ShowDocStrategyComposite strategyComposite;

    public ShowDocContext(ShowDocStrategyComposite strategyComposite) {
        this.strategyComposite = strategyComposite;
    }

    /**
     * 获取显示在swagger上的文档注释
     *
     * @param context
     * @param fieldType
     * @return
     */
    public String showDocDescInfo(ModelPropertyContext context, Class<?> fieldType) {
        return strategyComposite.showDocInfo(context, fieldType);
    }
}
