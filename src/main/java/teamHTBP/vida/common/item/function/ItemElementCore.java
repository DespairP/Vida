package teamHTBP.vida.common.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

public class ItemElementCore extends Item {
    public ItemElementCore() {
        super(new Properties().tab(VidaItemGroupLoader.vidaItemGroup));
    }
}
