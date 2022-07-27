package teamHTBP.vida.common.item.potion;

import net.minecraft.world.item.Item;
import teamHTBP.vida.common.item.VidaItemGroupLoader;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.api.common.item.IElementItem;

public class ItemCreativeElementPotion extends Item implements IElementItem {
    public final EnumElements ELEMENT_TYPE;

    public ItemCreativeElementPotion(EnumElements elementType) {
        super(new Item.Properties().tab(VidaItemGroupLoader.vidaItemGroup));
        this.ELEMENT_TYPE = elementType;
    }

    @Override
    public EnumElements getElement() {
        return ELEMENT_TYPE;
    }

}
