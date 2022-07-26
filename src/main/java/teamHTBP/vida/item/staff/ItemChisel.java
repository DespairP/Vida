package teamHTBP.vida.item.staff;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemChisel extends Item {
    public ItemChisel() {
        super(new Properties().maxStackSize(1).group(ItemGroupLoader.VIDA_ITEM_GROUP));
    }


}
