package teamHTBP.vida.common.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

public class ItemElementBottle extends Item {
    public ItemElementBottle() {
        super(new Properties().tab(VidaItemGroupLoader.vidaItemGroup).stacksTo(3));
    }
}
