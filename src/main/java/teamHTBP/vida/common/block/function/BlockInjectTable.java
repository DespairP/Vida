package teamHTBP.vida.common.block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import teamHTBP.vida.common.TileEntity.TileEntityInjectTable;

import javax.annotation.Nullable;

public class BlockInjectTable extends Block {
    public BlockInjectTable() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE).notSolid().harvestLevel(1).sound(SoundType.STONE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityInjectTable();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEntityInjectTable) {
                TileEntityInjectTable tileEntityInjectTable = (TileEntityInjectTable) tileEntity;
                if (!player.isSneaking() && handIn == Hand.MAIN_HAND) {
                    if (tileEntityInjectTable.setSwordItem(player.getHeldItem(Hand.MAIN_HAND)) && player.getHeldItem(Hand.MAIN_HAND) != ItemStack.EMPTY) {
                        player.getHeldItem(Hand.MAIN_HAND).shrink(1);
                        worldIn.notifyBlockUpdate(pos, state, state, 3);
                        return ActionResultType.SUCCESS;
                    } else if (tileEntityInjectTable.hasSwordItem()) {
                        NetworkHooks.openGui((ServerPlayerEntity) player, tileEntityInjectTable, (PacketBuffer packerBuffer) -> {
                            packerBuffer.writeItemStack(tileEntityInjectTable.getSwordStack());
                            packerBuffer.writeBlockPos(tileEntityInjectTable.getPos());
                        });
                        return ActionResultType.SUCCESS;
                    }
                } else if (player.isSneaking()) {
                    player.addItemStackToInventory(tileEntityInjectTable.getSwordStackToPlayer());
                    worldIn.notifyBlockUpdate(pos, state, state, 3);
                    return ActionResultType.SUCCESS;
                }
            }
        } else {
            return ActionResultType.CONSUME;
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
