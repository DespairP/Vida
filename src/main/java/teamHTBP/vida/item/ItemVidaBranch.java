package teamHTBP.vida.item;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemVidaBranch extends Item {
    public ItemVidaBranch() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }
}
