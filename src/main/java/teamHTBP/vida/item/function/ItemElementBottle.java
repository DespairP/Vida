package teamHTBP.vida.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class ItemElementBottle extends Item {
    public ItemElementBottle() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup).stacksTo(3));
    }
}
