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

public class ItemGuidebook extends Item {
    public ItemGuidebook() {
        super(new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, handIn);
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,() -> () -> GuidebookHelper.getManager().openGuidebook());
        return ActionResult.resultPass(itemstack);
    }
}
