package teamHTBP.vida.ItemGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import teamHTBP.vida.Item.ItemLoader;


public class VidaItemGroup extends ItemGroup {
    public VidaItemGroup() {
        super("Vida");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemLoader.vidawand.get());
    }
}
