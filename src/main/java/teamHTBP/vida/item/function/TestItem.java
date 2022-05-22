package teamHTBP.vida.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class TestItem extends Item {

    public TestItem() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }
}
