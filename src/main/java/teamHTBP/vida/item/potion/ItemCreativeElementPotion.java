package teamHTBP.vida.item.potion;

import net.minecraft.item.Item;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.IElementItem;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemCreativeElementPotion extends Item implements IElementItem {
    public final EnumElements ELEMENT_TYPE;

    public ItemCreativeElementPotion(EnumElements elementType) {
        super(new Item.Properties().tab(ItemGroupLoader.vidaItemGroup));
        this.ELEMENT_TYPE = elementType;
    }

    @Override
    public EnumElements getItemElement() {
        return ELEMENT_TYPE;
    }

}
