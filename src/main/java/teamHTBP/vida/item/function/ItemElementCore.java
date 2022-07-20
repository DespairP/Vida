package teamHTBP.vida.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemElementCore extends Item {
    public ItemElementCore() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }
}
