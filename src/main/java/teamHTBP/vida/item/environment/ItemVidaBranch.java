package teamHTBP.vida.item.environment;

import net.minecraft.world.item.Item;
import teamHTBP.vida.creativetab.ItemGroupLoader;

public class ItemVidaBranch extends Item {
    public ItemVidaBranch() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }
}
