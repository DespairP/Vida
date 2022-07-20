package teamHTBP.vida.item.staff;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class ItemPrism extends Item {
    public ItemPrism() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }
}
