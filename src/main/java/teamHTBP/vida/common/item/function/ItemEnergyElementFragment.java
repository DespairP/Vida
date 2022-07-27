package teamHTBP.vida.common.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

public class ItemEnergyElementFragment extends Item {
    public ItemEnergyElementFragment() {
        super(new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP).maxStackSize(16));
    }
}
