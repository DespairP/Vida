package teamHTBP.vida.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.creativetab.ItemGroupRegistry;

public class ItemEnergyElementFragment extends Item {
    public ItemEnergyElementFragment() {
        super(new Properties().tab(ItemGroupRegistry.vidaItemGroup).stacksTo(16));
    }
}
