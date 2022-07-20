package teamHTBP.vida.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class TestItem extends Item {

    public TestItem() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }
}
