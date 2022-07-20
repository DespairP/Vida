package teamHTBP.vida.item.environment;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class ItemVidaBranch extends Item {
    public ItemVidaBranch() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }
}
