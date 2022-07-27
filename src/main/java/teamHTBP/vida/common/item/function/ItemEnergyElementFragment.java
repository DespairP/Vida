package teamHTBP.vida.common.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

public class ItemEnergyElementFragment extends Item {
    public ItemEnergyElementFragment() {
        super(new Properties().tab(VidaItemGroupLoader.vidaItemGroup).stacksTo(16));
    }
}
