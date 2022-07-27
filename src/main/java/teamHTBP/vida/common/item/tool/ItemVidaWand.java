package teamHTBP.vida.common.item.tool;

import net.minecraft.world.item.Item;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

/**
 * 生命法杖
 *
 * @version 0.0.2
 **/
public class ItemVidaWand extends Item {
    public ItemVidaWand() {
        super(new Item.Properties().tab(VidaItemGroupLoader.vidaItemGroup).stacksTo(1));
    }
}