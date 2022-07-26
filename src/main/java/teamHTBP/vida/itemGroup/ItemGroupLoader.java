package teamHTBP.vida.itemGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import teamHTBP.vida.item.ItemLoader;

public class ItemGroupLoader {
    public final static ItemGroup VIDA_ITEM_GROUP = new ItemGroup("vida") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemLoader.WAND_VIDA.get());
        }
    };
    
    
}
