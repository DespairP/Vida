package teamHTBP.vida.item.Potion;

import net.minecraft.item.Item;
import teamHTBP.vida.item.IElementItem;
import teamHTBP.vida.itemGroup.ItemGroupLoader;
import teamHTBP.vida.helper.EnumElements;

public class ItemCreativeElementPotion extends Item implements IElementItem {
    public final EnumElements ELEMENT_TYPE;

    public ItemCreativeElementPotion(EnumElements elementType) {
        super(new Item.Properties().group(ItemGroupLoader.vidaItemGroup));
        this.ELEMENT_TYPE = elementType;
    }

    @Override
    public EnumElements getItemElement(){return ELEMENT_TYPE;}

}
