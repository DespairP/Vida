package teamHTBP.vida.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class TestItem extends Item {

    public TestItem() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }
}
