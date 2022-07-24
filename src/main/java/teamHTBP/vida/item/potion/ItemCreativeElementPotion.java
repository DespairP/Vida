package teamHTBP.vida.item.potion;

import net.minecraft.world.item.Item;
import teamHTBP.vida.creativetab.ItemGroupRegistry;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.item.IElementItem;

public class ItemCreativeElementPotion extends Item implements IElementItem {
    public final EnumElements ELEMENT_TYPE;

    public ItemCreativeElementPotion(EnumElements elementType) {
        super(new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup));
        this.ELEMENT_TYPE = elementType;
    }

    @Override
    public EnumElements getElement() {
        return ELEMENT_TYPE;
    }

}
