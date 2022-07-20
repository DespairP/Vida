package teamHTBP.vida.itemGroup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.item.ItemLoader;


public class VidaItemGroup extends CreativeModeTab {
    public VidaItemGroup() {
        super("Vida");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemLoader.WAND_VIDA.get());
    }
}
