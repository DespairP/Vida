package teamHTBP.vida.block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import teamHTBP.vida.TileEntity.TileEntityPrismTable;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class BlockPrismTable extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    private final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 13, 16);


    public BlockPrismTable() {
        super(Properties.of(Material.WOOD).strength(3.0f, 3.0f).noOcclusion().harvestTool(ToolType.PICKAXE).noOcclusion());
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
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
            TileEntityPrismTable tileEntityPrismTable = (TileEntityPrismTable) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, tileEntityPrismTable, (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(tileEntityPrismTable.getBlockPos());
            });
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntityPrismTable tileEntityPrismTable = (TileEntityPrismTable) worldIn.getBlockEntity(pos);
        if (tileEntityPrismTable != null) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityPrismTable.getSlot().getItem(0)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityPrismTable.getSlot().getItem(1)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityPrismTable.getSlot().getItem(2)));
        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }


}
