package teamHTBP.vida.Item.staff;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

public class ItemChisel extends Item {
    public ItemChisel() {
        super(new Properties().maxStackSize(1).group(ItemGroupLoader.vidaItemGroup));
    }


}
