package teamHTBP.vida.Item;

import net.minecraft.item.Item;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

public class ItemFaintLight extends Item {
    public ItemFaintLight() {
        super(new Properties().maxStackSize(1).group(ItemGroupLoader.vidaItemGroup));
    }
}
