package teamHTBP.vida.common.item.environment;

import net.minecraft.world.item.Item;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

public class ItemVidaBranch extends Item {
    public ItemVidaBranch() {
        super(new Properties().tab(VidaItemGroupLoader.vidaItemGroup));
    }
}
