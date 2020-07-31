package teamHTBP.vida.Item;

import net.minecraft.item.Item;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

public class ItemEnergyElementFragment extends Item {
    public ItemEnergyElementFragment() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup).maxStackSize(16));
    }
}
