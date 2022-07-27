package teamHTBP.vida.api.common.block;

import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.api.core.element.IElement;

/**
 * 表示这是一个装饰性方块
 */
public interface IDecoBlock extends IElementBlock {
    /**
     * 如果装饰性方块有元素
     * @return 元素
     */
    @Override
    default IElement getElement() {
        return EnumElements.NONE;
    }
}
