package teamHTBP.vida.itemGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import teamHTBP.vida.item.ItemLoader;


public class VidaItemGroup extends ItemGroup {
    public VidaItemGroup() {
        super("Vida");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemLoader.WAND_VIDA.get());
    }
}
