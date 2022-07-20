package teamHTBP.vida.item.function;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import teamHTBP.vida.entity.EntityFaintLight;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.creativetab.ItemGroupLoader;

public class ItemFaintLight extends Item {
    public int element = 1;

    public ItemFaintLight() {
        super(new Properties().stacksTo(1).tab(ItemGroupLoader.vidaItemGroup));
    }

    public ItemFaintLight(int element) {
        super(new Properties().stacksTo(1).tab(ItemGroupLoader.vidaItemGroup));
        this.element = element;
    }


    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        HitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
        if (raytraceresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else if (worldIn.isClientSide) {
            System.out.println("sssss");
            return InteractionResultHolder.success(itemstack);
        } else {
            BlockHitResult blockraytraceresult = (BlockHitResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
                return InteractionResultHolder.pass(itemstack);
            } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
                EntityFaintLight entityFaintLight = new EntityFaintLight(EntityLoader.faintLight.get(), worldIn);
                entityFaintLight.setPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                entityFaintLight.setFaintLightType(EnumElements.values()[element]);
                if (!worldIn.addFreshEntity(entityFaintLight)) {
                    return InteractionResultHolder.pass(itemstack);
                } else {

                    if (!playerIn.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.success(itemstack);
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();

            BlockPos blockpos1;
            if (blockstate.getBlockSupportShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }
            EntityFaintLight entityFaintLight = new EntityFaintLight(EntityLoader.faintLight.get(), world);
            entityFaintLight.setPos(blockpos1.getX() + 0.5, blockpos1.getY(), blockpos1.getZ() + 0.5);
            entityFaintLight.setFaintLightType(EnumElements.values()[element]);
            if (world.addFreshEntity(entityFaintLight)) {
                itemstack.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }
    }

}
