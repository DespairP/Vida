package teamHTBP.vida.Item;

import net.minecraft.item.Item;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

public class ItemVidaBranch extends Item {
    public ItemVidaBranch() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }
}
