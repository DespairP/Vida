package teamHTBP.vida.common.item.function;

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
import teamHTBP.vida.common.item.VidaItemGroupLoader;
import teamHTBP.vida.common.entity.FaintLight;
import teamHTBP.vida.common.entity.VidaEntityLoader;
import teamHTBP.vida.core.element.EnumElements;

public class ItemFaintLight extends Item {
    public int element = 1;

    public ItemFaintLight() {
        super(new Properties().stacksTo(1).tab(VidaItemGroupLoader.vidaItemGroup));
    }

    public ItemFaintLight(int element) {
        super(new Properties().stacksTo(1).tab(VidaItemGroupLoader.vidaItemGroup));
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
                FaintLight faintLight = new FaintLight(VidaEntityLoader.faintLight.get(), worldIn);
                faintLight.setPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                faintLight.setFaintLightType(EnumElements.values()[element]);
                if (!worldIn.addFreshEntity(faintLight)) {
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
            FaintLight faintLight = new FaintLight(VidaEntityLoader.faintLight.get(), world);
            faintLight.setPos(blockpos1.getX() + 0.5, blockpos1.getY(), blockpos1.getZ() + 0.5);
            faintLight.setFaintLightType(EnumElements.values()[element]);
            if (world.addFreshEntity(faintLight)) {
                itemstack.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }
    }

}
