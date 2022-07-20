package teamHTBP.vida.item.function;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class ItemBluePrint extends Item {
    private int rarity = 1; //稀有度

    public ItemBluePrint(int rarity) {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup).stacksTo(1));
        this.rarity = rarity;
    }

    @Override
    public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        super.onCraftedBy(stack, worldIn, playerIn);
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putInt("complete", 0); //设置完成度
        nbt.putInt("rarity", rarity); //设置稀有度
    }


}
