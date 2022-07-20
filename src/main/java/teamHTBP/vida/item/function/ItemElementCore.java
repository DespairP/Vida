package teamHTBP.vida.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class ItemElementCore extends Item {
    public ItemElementCore() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }
}
