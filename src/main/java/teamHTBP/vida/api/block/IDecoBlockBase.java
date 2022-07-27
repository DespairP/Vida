package teamHTBP.vida.api.block;

import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.api.core.element.IElement;

/**Vida装饰性方块接口*/
public interface IDecoBlockBase extends IElementBlock{
    /**装饰性方块有元素*/
    default IElement getElement() {
        return EnumElements.NONE;
    }
}
