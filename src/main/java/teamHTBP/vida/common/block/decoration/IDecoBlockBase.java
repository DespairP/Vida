package teamHTBP.vida.common.block.decoration;

import teamHTBP.vida.core.element.EnumElements;

/**
 * 表示这是一个装饰性方块
 */
public interface IDecoBlockBase {
    /**
     * 如果装饰性方块有元素
     */
    default EnumElements getElement() {
        return EnumElements.NONE;
    }
}
