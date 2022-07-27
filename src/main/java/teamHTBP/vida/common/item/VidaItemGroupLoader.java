package teamHTBP.vida.common.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.Vida;

public class VidaItemGroupLoader {
    public static CreativeModeTab vidaItemGroup = new CreativeModeTab(Vida.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(VidaItemLoader.WAND_VIDA.get());
        }
    };
}
