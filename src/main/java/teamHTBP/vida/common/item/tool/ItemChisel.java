package teamHTBP.vida.common.item.tool;

import net.minecraft.world.item.Item;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

public class ItemChisel extends Item {
    public ItemChisel() {
        super(new Properties().stacksTo(1).tab(VidaItemGroupLoader.vidaItemGroup));
    }


}
