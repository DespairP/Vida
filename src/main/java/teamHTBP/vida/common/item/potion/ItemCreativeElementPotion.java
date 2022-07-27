package teamHTBP.vida.common.item.potion;

import net.minecraft.item.Item;
import teamHTBP.vida.api.core.element.IElement;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.api.item.IElementItem;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

public class ItemCreativeElementPotion extends Item implements IElementItem {
    public final EnumElements elementType;

    public ItemCreativeElementPotion(EnumElements elementType) {
        super(new Item.Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
        this.elementType = elementType;
    }

    @Override
    public IElement getElement() {
        return elementType;
    }

}
