package teamHTBP.vida.Block.function;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;

import javax.annotation.Nullable;

/*元素祭坛
@Version 0.0.1*/
public class BlockElementCoreAltar extends Block {
    public BlockElementCoreAltar(){
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
            if (handIn == Hand.MAIN_HAND && !player.isSneaking()) {
                if (player.inventory.getCurrentItem() != ItemStack.EMPTY) {
                    //先检测是不是核心物品
                    boolean result = tileEntityElementCoreAltar.setCoreItemStack(new ItemStack(player.inventory.getCurrentItem().getItem(), 1));
                    if (result) {
                        this.decreasePlayerItem(player);
                        worldIn.notifyBlockUpdate(pos, state, state, 3);
                        return ActionResultType.SUCCESS;
                    } else {
                        //不是的话放入祭坛物品中
                        result = tileEntityElementCoreAltar.setAltarItemStack(new ItemStack(player.inventory.getCurrentItem().getItem(), 1));
                        if (result) {
                            this.decreasePlayerItem(player);
                            worldIn.notifyBlockUpdate(pos, state, state, 3);
                            return ActionResultType.SUCCESS;
                        }else
                            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                    }
                }
                //拿到祭坛中的物品
            }else if(handIn == Hand.MAIN_HAND && player.isSneaking()){
                ItemStack itemStack = tileEntityElementCoreAltar.getAltarItemStack();
                if(itemStack != ItemStack.EMPTY){
                    player.inventory.addItemStackToInventory(itemStack);
                    itemStack = ItemStack.EMPTY;
                    worldIn.notifyBlockUpdate(pos, state, state, 3);
                }else
                    return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
            }else
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }


    public void decreasePlayerItem(PlayerEntity player){
        if(player.isCreative()){
            return;
        }else{
            int count = player.inventory.getCurrentItem().getCount();
            player.inventory.getCurrentItem().setCount(count - 1);
        }
    }

}


