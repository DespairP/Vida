package teamHTBP.vida.item.staff;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class ItemChisel extends Item {
    public ItemChisel() {
        super(new Properties().stacksTo(1).tab(ItemGroupLoader.vidaItemGroup));
    }


}
