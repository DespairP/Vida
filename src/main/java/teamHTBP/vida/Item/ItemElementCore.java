package teamHTBP.vida.Item;

import net.minecraft.item.Item;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

public class ItemElementCore extends Item {
    public ItemElementCore() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }
}
