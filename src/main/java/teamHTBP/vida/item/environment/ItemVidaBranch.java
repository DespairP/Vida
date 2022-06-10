package teamHTBP.vida.item.environment;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemVidaBranch extends Item {
    public ItemVidaBranch() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }
}
