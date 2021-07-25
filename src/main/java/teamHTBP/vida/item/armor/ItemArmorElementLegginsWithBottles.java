package teamHTBP.vida.item.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ItemArmorElementLegginsWithBottles extends ItemArmorElementLeggings {
    public ItemArmorElementLegginsWithBottles(int element){
        super(element);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(!worldIn.isRemote){
            if(handIn == Hand.MAIN_HAND && playerIn.isSneaking()){
                return super.onItemRightClick(worldIn, playerIn, handIn);
            }else if(handIn == Hand.MAIN_HAND) {
                ItemStack stack = playerIn.inventory.getCurrentItem();
                ItemArmorBottlesContainerProvider provider = new ItemArmorBottlesContainerProvider(stack);
                NetworkHooks.openGui((ServerPlayerEntity) playerIn, provider, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeItemStack(stack);
                });
                return ActionResult.resultSuccess(stack);
            }
        }
         return ActionResult.resultPass(itemstack);
    }



}
