package teamHTBP.vida.item.function;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class ItemGuidebook extends Item {
    public ItemGuidebook() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isClientSide) return super.use(worldIn, playerIn, handIn);
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,() -> () -> GuidebookHelper.getManager().openGuidebook());
        return ActionResult.pass(itemstack);
    }
}
