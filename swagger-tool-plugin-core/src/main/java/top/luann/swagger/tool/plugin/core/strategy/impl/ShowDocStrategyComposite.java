package top.luann.swagger.tool.plugin.core.strategy.impl;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import top.luann.swagger.tool.plugin.core.strategy.ShowDocStrategist;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聚合了ShowDocStrategy实现的类
 * <p>
 * 参考HandlerMethodArgumentResolverComposite实现
 *
 * @author luHan
 * @create 2021/12/13 17:32
 * @since 1.0.0
 */
@Component
public class ShowDocStrategyComposite implements ShowDocStrategist {
    private final Logger log = LoggerFactory.getLogger(ShowDocStrategyComposite.class);

    private final List<ShowDocStrategist> showDocStrategists;

    public ShowDocStrategyComposite(List<ShowDocStrategist> showDocStrategists) {
        this.showDocStrategists = showDocStrategists;
    }

    private final Map<String, ShowDocStrategist> showDocStrategistsCache =
            new ConcurrentHashMap<>(256);


    @Override
    public boolean support(AnnotatedField field) {
        return getShowDocStrategist(field) != null;
    }

    @Override
    public String showDocInfo(ModelPropertyContext context, Class<?> fieldType) {
        AnnotatedField field = context.getBeanPropertyDefinition().get().getField();
        ShowDocStrategist strategist = getShowDocStrategist(field);
        if (strategist == null) {
            log.warn("Unsupported field type [" + fieldType + "]. supportsParameter should be called first.");
            return null;
        }
        return strategist.showDocInfo(context, fieldType);
    }

    /**
     * 添加一个ShowDocStrategist实现类
     *
     * @param showDocStrategist
     * @return
     */
    public ShowDocStrategyComposite addStrategist(ShowDocStrategist showDocStrategist) {
        showDocStrategists.add(showDocStrategist);
        return this;
    }

    private ShowDocStrategist getShowDocStrategist(AnnotatedField field) {
        ShowDocStrategist result = this.showDocStrategistsCache.get(field.getFullName());
        if (result == null) {
            for (ShowDocStrategist strategist : this.showDocStrategists) {
                if (strategist.support(field)) {
                    result = strategist;
                    this.showDocStrategistsCache.put(field.getFullName(), result);
                    break;
                }
            }
        }
        return result;
    }
}
