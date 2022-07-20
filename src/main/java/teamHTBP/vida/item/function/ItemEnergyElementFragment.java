package teamHTBP.vida.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemEnergyElementFragment extends Item {
    public ItemEnergyElementFragment() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup).stacksTo(16));
    }
}
