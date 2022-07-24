package teamHTBP.vida.creativetab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.item.VidaItemRegistry;


public class VidaItemGroup extends CreativeModeTab {
    public VidaItemGroup() {
        super("Vida");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VidaItemRegistry.WAND_VIDA.get());
    }
}
