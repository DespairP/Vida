package teamHTBP.vida.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemEnergyElementFragment extends Item {
    public ItemEnergyElementFragment() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup).maxStackSize(16));
    }
}
