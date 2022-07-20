package teamHTBP.vida.item.armor;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class ItemArmorElementLegginsWithBottles extends ItemArmorElementLeggings {
    public ItemArmorElementLegginsWithBottles(int element) {
        super(element);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide) {
            if (handIn == InteractionHand.MAIN_HAND && playerIn.isShiftKeyDown()) {
                return super.use(worldIn, playerIn, handIn);
            } else if (handIn == InteractionHand.MAIN_HAND) {
                ItemStack stack = playerIn.getInventory().getSelected();
                ItemArmorBottlesContainerProvider provider = new ItemArmorBottlesContainerProvider(stack);
                NetworkHooks.openGui((ServerPlayer) playerIn, provider, (FriendlyByteBuf packerBuffer) -> {
                    packerBuffer.writeItem(stack);
                });
                return InteractionResultHolder.success(stack);
            }
        }
        return InteractionResultHolder.pass(itemstack);
    }


}
