package teamHTBP.vida.Item;

import net.minecraft.item.Item;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

public class ItemElementBottle extends Item {
    public ItemElementBottle() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup).maxStackSize(3));
    }
}
