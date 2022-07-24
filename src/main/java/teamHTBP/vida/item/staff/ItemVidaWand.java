package teamHTBP.vida.item.staff;

import net.minecraft.world.item.Item;
import teamHTBP.vida.creativetab.ItemGroupRegistry;

/**
 * 生命法杖
 *
 * @version 0.0.2
 **/
public class ItemVidaWand extends Item {
    public ItemVidaWand() {
        super(new Item.Properties().tab(ItemGroupRegistry.vidaItemGroup).stacksTo(1));
    }
}