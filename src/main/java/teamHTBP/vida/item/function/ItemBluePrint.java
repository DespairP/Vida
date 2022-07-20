package teamHTBP.vida.item.function;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemBluePrint extends Item {
    private int rarity = 1; //稀有度

    public ItemBluePrint(int rarity) {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup).stacksTo(1));
        this.rarity = rarity;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) {
        super.onCraftedBy(stack, worldIn, playerIn);
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt("complete", 0); //设置完成度
        nbt.putInt("rarity", rarity); //设置稀有度
    }


}
