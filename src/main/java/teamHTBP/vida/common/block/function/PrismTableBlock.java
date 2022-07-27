package teamHTBP.vida.common.block.function;

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
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.blockentity.PrismTableBlockEntity;

import javax.annotation.Nullable;

public class PrismTableBlock extends VidaBaseEntityBlock<PrismTableBlockEntity> {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 13, 16);


    public PrismTableBlock() {
        super(Properties.of(Material.WOOD).strength(3.0f, 3.0f).noOcclusion()
                .noOcclusion(), VidaBlockEntityLoader.TileEntityPrismTable);
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            PrismTableBlockEntity prismTableBlockEntity = (PrismTableBlockEntity) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayer) player, prismTableBlockEntity, (FriendlyByteBuf packerBuffer) -> {
                packerBuffer.writeBlockPos(prismTableBlockEntity.getBlockPos());
            });
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        PrismTableBlockEntity prismTableBlockEntity = (PrismTableBlockEntity) worldIn.getBlockEntity(pos);
        if (prismTableBlockEntity != null) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), prismTableBlockEntity.getSlot().getItem(0)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), prismTableBlockEntity.getSlot().getItem(1)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), prismTableBlockEntity.getSlot().getItem(2)));
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
