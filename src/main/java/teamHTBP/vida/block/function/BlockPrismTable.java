package teamHTBP.vida.block.function;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import teamHTBP.vida.block.base.ModBaseEntityBlock;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.TileEntityPrismTable;

import javax.annotation.Nullable;

public class BlockPrismTable extends ModBaseEntityBlock<TileEntityPrismTable> {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 13, 16);


    public BlockPrismTable() {
        super(Properties.of(Material.WOOD).strength(3.0f, 3.0f).noOcclusion()
                // todo tag .harvestTool(ToolType.PICKAXE)
                .noOcclusion(), TileEntityLoader.TileEntityPrismTable);
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            TileEntityPrismTable tileEntityPrismTable = (TileEntityPrismTable) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayer) player, tileEntityPrismTable, (FriendlyByteBuf packerBuffer) -> {
                packerBuffer.writeBlockPos(tileEntityPrismTable.getBlockPos());
            });
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        TileEntityPrismTable tileEntityPrismTable = (TileEntityPrismTable) worldIn.getBlockEntity(pos);
        if (tileEntityPrismTable != null) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityPrismTable.getSlot().getItem(0)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityPrismTable.getSlot().getItem(1)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityPrismTable.getSlot().getItem(2)));
        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }
}
