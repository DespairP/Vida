package teamHTBP.vida.common.block.function;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import teamHTBP.vida.common.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.core.element.IElement;
import teamHTBP.vida.common.item.ItemLoader;
import teamHTBP.vida.common.particle.CubeParticleData;

import javax.annotation.Nullable;
import java.util.Random;

/*元素祭坛
@Version 0.0.1*/
public class BlockElementCoreAltar extends Block {
    public BlockElementCoreAltar() {
        super(Properties.create(Material.IRON).notSolid().hardnessAndResistance(5.0f, 5.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE));

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityElementCoreAltar();
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntityElementCoreAltar tileEntityElementCoreAltar = (TileEntityElementCoreAltar) worldIn.getTileEntity(pos);
            Item handItem = player.inventory.getCurrentItem().getItem();
            boolean handItemResult = (handItem == ItemLoader.WAND_VIDA.get());
            if (handIn == Hand.MAIN_HAND && !player.isSneaking() && !handItemResult) {
                if (player.inventory.getCurrentItem() != ItemStack.EMPTY) {
                    //先检测是不是核心物品
                    ItemStack stack = player.inventory.getCurrentItem().copy();
                    stack.setCount(1);
                    boolean result = tileEntityElementCoreAltar.setCoreItemStack(stack);
                    if (result) {
                        this.decreasePlayerItem(player);
                        worldIn.notifyBlockUpdate(pos, state, state, 3);
                        return ActionResultType.SUCCESS;
                    } else {
                        //不是的话放入祭坛物品中
                        stack = player.inventory.getCurrentItem().copy();
                        stack.setCount(1);
                        result = tileEntityElementCoreAltar.setAltarItemStack(stack);
                        if (result) {
                            this.decreasePlayerItem(player);
                            worldIn.notifyBlockUpdate(pos, state, state, 3);
                            return ActionResultType.SUCCESS;
                        } else
                            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                    }
                }
                //拿到祭坛中的物品
            } else if (handIn == Hand.MAIN_HAND && player.isSneaking() && !handItemResult) {
                ItemStack itemStack = tileEntityElementCoreAltar.getAltarItemStack();
                if (itemStack != ItemStack.EMPTY || !itemStack.isEmpty()) {
                    player.inventory.addItemStackToInventory(itemStack);
                }
                worldIn.notifyBlockUpdate(pos, state, state, 3);
            } else if (!player.isSneaking() && handItemResult) {
                //如果法杖右键的话，开始检测
                tileEntityElementCoreAltar.isWAND_VIDACilck = true;
                worldIn.notifyBlockUpdate(pos, state, state, 3);
                return ActionResultType.SUCCESS;
            } else
                return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }


    public void decreasePlayerItem(PlayerEntity player) {
        if (player.isCreative()) {
            return;
        } else {
            player.inventory.getCurrentItem().shrink(1);
        }
    }

    /**当祭坛被破坏时,内部物品掉出*/
    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntityElementCoreAltar tileEntityElementCoreAltar = (TileEntityElementCoreAltar) worldIn.getTileEntity(pos);
        if (tileEntityElementCoreAltar != null && !worldIn.isRemote) {
            // 如果被破坏掉出祭坛物品
            for(ItemStack altarItem : tileEntityElementCoreAltar.altarItem){
                if(!altarItem.isEmpty()){
                    worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), altarItem));
                }
            }
            // 核心物品
            if (tileEntityElementCoreAltar.coreItem != ItemStack.EMPTY && !tileEntityElementCoreAltar.coreItem.isEmpty())
                worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityElementCoreAltar.coreItem));

            // 清空祭坛内物品
            tileEntityElementCoreAltar.altarItem.clear();
            tileEntityElementCoreAltar.coreItem = ItemStack.EMPTY;
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }


    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextBoolean()) {
            TileEntityElementCoreAltar tileEntityElementCoreAltar = (TileEntityElementCoreAltar) worldIn.getTileEntity(pos);
            if (tileEntityElementCoreAltar != null && tileEntityElementCoreAltar.isProgressing && tileEntityElementCoreAltar.moveup >= 0.8f) {
                IElement element = tileEntityElementCoreAltar.element;
                if (element instanceof EnumElements && rand.nextFloat() >= 0.65D) {
                    double speedX = rand.nextBoolean() ? 0 - rand.nextFloat() / 1000.0F : 0 + rand.nextFloat() / 1000.0F;
                    double speedY = 0.010 + rand.nextFloat() / 1000f;
                    double speedZ = rand.nextBoolean() ? 0 + rand.nextFloat() / 1000.0f : 0 - rand.nextFloat() / 1000.0f;
                    double posX = rand.nextBoolean() ? pos.getX() + 0.5f + rand.nextFloat() / 4 : pos.getX() + 0.5f - rand.nextFloat() / 4;
                    double posY = rand.nextBoolean() ? pos.getY() + 1.2F + rand.nextFloat() / 4 : pos.getY() + 1.2F - rand.nextFloat() / 4;
                    double posZ = rand.nextBoolean() ? pos.getZ() + 0.5f + rand.nextFloat() / 4 : pos.getZ() + 0.5f - rand.nextFloat() / 4;
                    float r = 1;
                    float g = 1;
                    float b = 1;
                    switch ((EnumElements) element) {
                        case GOLD:
                            r = 255;
                            g = 255;
                            b = rand.nextInt(30) + 180;
                            break;
                        case WOOD:
                            r = 73;
                            g = 175;
                            b = 92;
                            break;
                        case AQUA:
                            r = 73;
                            g = 203;
                            b = 255;
                            break;
                        case FIRE:
                            r = 255;
                            g = 30;
                            b = 43;
                            break;
                        case EARTH:
                            r = 186;
                            g = 184;
                            b = 111;
                            break;
                    }
                    worldIn.addParticle(new CubeParticleData(r, g, b, 0.03f), posX, posY, posZ, speedX, speedY, speedZ);
                }
            }
        }

    }
}


