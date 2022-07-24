package teamHTBP.vida.item.function;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import teamHTBP.vida.creativetab.ItemGroupRegistry;
import teamHTBP.vida.helper.guidebook.GuidebookHelper;

public class ItemGuidebook extends Item {
    public ItemGuidebook() {
        super(new Properties().tab(ItemGroupRegistry.vidaItemGroup));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if(!worldIn.isClientSide) return super.use(worldIn, playerIn, handIn);
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,() -> () -> GuidebookHelper.getManager().openGuidebook());
        return InteractionResultHolder.pass(itemstack);
    }
}
