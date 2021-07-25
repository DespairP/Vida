package teamHTBP.vida.item;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemElementBottle extends Item {
    public ItemElementBottle() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup).maxStackSize(3));
    }
}
