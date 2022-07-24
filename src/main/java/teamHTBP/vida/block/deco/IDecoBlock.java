package teamHTBP.vida.block.deco;

import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.element.IElement;

/**
 * 表示这是一个装饰性方块
 */
public interface IDecoBlock {
    /**
     * 如果装饰性方块有元素
     * @return 元素
     */
    default IElement getElement() {
        return EnumElements.NONE;
    }
}
