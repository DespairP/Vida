package teamHTBP.vida.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import teamHTBP.vida.api.common.blockentity.ITickableBlockEntity;
import teamHTBP.vida.common.blockentity.base.VidaBaseBlockEntity;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

/**
 * @author DustW
 */
@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class VidaBaseEntityBlock<I extends BlockEntity> extends Block implements EntityBlock {
    Supplier<BlockEntityType<I>> entityTypeSupplier;

    public VidaBaseEntityBlock(Properties properties, Supplier<BlockEntityType<I>> object) {
        super(properties);
        this.entityTypeSupplier = object;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return entityTypeSupplier.get().create(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == entityTypeSupplier.get() ? ITickableBlockEntity.getTicker() : null;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            if (pLevel.getBlockEntity(pPos) instanceof VidaBaseBlockEntity vbe) {
                vbe.getDrops().forEach(item ->
                        Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), item));
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }
}
