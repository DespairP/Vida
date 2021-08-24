package teamHTBP.vida.item;

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
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.entity.EntityFaintLight;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemFaintLight extends Item {
    public int element = 1;

    public ItemFaintLight() {
        super(new Properties().maxStackSize(1).group(ItemGroupLoader.vidaItemGroup));
    }

    public ItemFaintLight(int element) {
        super(new Properties().maxStackSize(1).group(ItemGroupLoader.vidaItemGroup));
        this.element = element;
    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.resultPass(itemstack);
        } else if (worldIn.isRemote) {
            System.out.println("sssss");
            return ActionResult.resultSuccess(itemstack);
        } else {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
                return ActionResult.resultPass(itemstack);
            } else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
                EntityFaintLight entityFaintLight = new EntityFaintLight(EntityLoader.faintLight.get(), worldIn);
                entityFaintLight.setPosition(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                entityFaintLight.setFaintLightType(EnumElements.values()[element]);
                if (!worldIn.addEntity(entityFaintLight)) {
                    return ActionResult.resultPass(itemstack);
                } else {

                    if (!playerIn.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    playerIn.addStat(Stats.ITEM_USED.get(this));
                    return ActionResult.resultSuccess(itemstack);
                }
            } else {
                return ActionResult.resultFail(itemstack);
            }
        }
    }

    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack itemstack = context.getItem();
            BlockPos blockpos = context.getPos();
            Direction direction = context.getFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.offset(direction);
            }
            EntityFaintLight entityFaintLight = new EntityFaintLight(EntityLoader.faintLight.get(), world);
            entityFaintLight.setPosition(blockpos1.getX() + 0.5, blockpos1.getY(), blockpos1.getZ() + 0.5);
            entityFaintLight.setFaintLightType(EnumElements.values()[element]);
            if (world.addEntity(entityFaintLight)) {
                itemstack.shrink(1);
            }

            return ActionResultType.SUCCESS;
        }
    }

}
