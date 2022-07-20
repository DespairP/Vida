package teamHTBP.vida.block.function;

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
import teamHTBP.vida.TileEntity.TileEntityInjectTable;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class BlockInjectTable extends Block {
    public BlockInjectTable() {
        super(Properties.of(Material.STONE).strength(3.0f, 3.0f).harvestTool(ToolType.PICKAXE).noOcclusion().harvestLevel(1).sound(SoundType.STONE));
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
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide) {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof TileEntityInjectTable) {
                TileEntityInjectTable tileEntityInjectTable = (TileEntityInjectTable) tileEntity;
                if (!player.isShiftKeyDown() && handIn == Hand.MAIN_HAND) {
                    if (tileEntityInjectTable.setSwordItem(player.getItemInHand(Hand.MAIN_HAND)) && player.getItemInHand(Hand.MAIN_HAND) != ItemStack.EMPTY) {
                        player.getItemInHand(Hand.MAIN_HAND).shrink(1);
                        worldIn.sendBlockUpdated(pos, state, state, 3);
                        return ActionResultType.SUCCESS;
                    } else if (tileEntityInjectTable.hasSwordItem()) {
                        NetworkHooks.openGui((ServerPlayerEntity) player, tileEntityInjectTable, (PacketBuffer packerBuffer) -> {
                            packerBuffer.writeItem(tileEntityInjectTable.getSwordStack());
                            packerBuffer.writeBlockPos(tileEntityInjectTable.getBlockPos());
                        });
                        return ActionResultType.SUCCESS;
                    }
                } else if (player.isShiftKeyDown()) {
                    player.addItem(tileEntityInjectTable.getSwordStackToPlayer());
                    worldIn.sendBlockUpdated(pos, state, state, 3);
                    return ActionResultType.SUCCESS;
                }
            }
        } else {
            return ActionResultType.CONSUME;
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}
