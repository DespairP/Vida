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
    public ItemArmorElementLegginsWithBottles(int element) {
        super(element);
    }


    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide) {
            if (handIn == Hand.MAIN_HAND && playerIn.isShiftKeyDown()) {
                return super.use(worldIn, playerIn, handIn);
            } else if (handIn == Hand.MAIN_HAND) {
                ItemStack stack = playerIn.inventory.getSelected();
                ItemArmorBottlesContainerProvider provider = new ItemArmorBottlesContainerProvider(stack);
                NetworkHooks.openGui((ServerPlayerEntity) playerIn, provider, (PacketBuffer packerBuffer) -> {
                    packerBuffer.writeItem(stack);
                });
                return ActionResult.success(stack);
            }
        }
        return ActionResult.pass(itemstack);
    }


}
