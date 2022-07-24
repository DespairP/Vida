package teamHTBP.vida.item.staff;

import net.minecraft.world.item.Item;
import teamHTBP.vida.creativetab.ItemGroupRegistry;

public class ItemChisel extends Item {
    public ItemChisel() {
        super(new Properties().stacksTo(1).tab(ItemGroupRegistry.vidaItemGroup));
    }


}
