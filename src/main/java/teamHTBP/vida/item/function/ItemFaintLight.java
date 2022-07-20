package teamHTBP.vida.item.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import teamHTBP.vida.entity.EntityFaintLight;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import net.minecraft.item.Item.Properties;

public class ItemFaintLight extends Item {
    public int element = 1;

    public ItemFaintLight() {
        super(new Properties().stacksTo(1).tab(ItemGroupLoader.vidaItemGroup));
    }

    public ItemFaintLight(int element) {
        super(new Properties().stacksTo(1).tab(ItemGroupLoader.vidaItemGroup));
        this.element = element;
    }


    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemstack);
        } else if (worldIn.isClientSide) {
            System.out.println("sssss");
            return ActionResult.success(itemstack);
        } else {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
                return ActionResult.pass(itemstack);
            } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
                EntityFaintLight entityFaintLight = new EntityFaintLight(EntityLoader.faintLight.get(), worldIn);
                entityFaintLight.setPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                entityFaintLight.setFaintLightType(EnumElements.values()[element]);
                if (!worldIn.addFreshEntity(entityFaintLight)) {
                    return ActionResult.pass(itemstack);
                } else {

                    if (!playerIn.abilities.instabuild) {
                        itemstack.shrink(1);
                    }
                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.success(itemstack);
                }
            } else {
                return ActionResult.fail(itemstack);
            }
        }
    }

    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
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

            return ActionResultType.SUCCESS;
        }
    }

}
