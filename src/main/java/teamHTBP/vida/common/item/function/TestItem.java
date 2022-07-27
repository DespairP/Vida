package teamHTBP.vida.common.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

public class TestItem extends Item {

    public TestItem() {
        super(new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
    }
}
