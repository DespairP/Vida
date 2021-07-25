package teamHTBP.vida.item;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemGemElement extends Item {
    public ItemGemElement() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }
}
