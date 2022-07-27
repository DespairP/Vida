package teamHTBP.vida.common.item.potion;

import net.minecraft.item.Item;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.common.item.IElementItem;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

public class ItemCreativeElementPotion extends Item implements IElementItem {
    public final EnumElements ELEMENT_TYPE;

    public ItemCreativeElementPotion(EnumElements elementType) {
        super(new Item.Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
        this.ELEMENT_TYPE = elementType;
    }

    @Override
    public EnumElements getItemElement() {
        return ELEMENT_TYPE;
    }

}
