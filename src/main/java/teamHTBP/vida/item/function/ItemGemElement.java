package teamHTBP.vida.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemGemElement extends Item {
    public ItemGemElement() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }
}
