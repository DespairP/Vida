package teamHTBP.vida.Block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import teamHTBP.vida.TileEntity.TileEntityPrismTable;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;

import javax.annotation.Nullable;

public class BlockPrismTable extends Block {
    private final VoxelShape SHAPE =Block.makeCuboidShape(0, 0, 0, 16, 13, 16);

    public BlockPrismTable() {
        super(Properties.create(Material.WOOD).hardnessAndResistance(3.0f, 3.0f).notSolid().harvestTool(ToolType.PICKAXE).notSolid());
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityPrismTable();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            TileEntityPrismTable tileEntityPrismTable = (TileEntityPrismTable) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, tileEntityPrismTable, (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(tileEntityPrismTable.getPos());
            });
        }
        return ActionResultType.SUCCESS;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
