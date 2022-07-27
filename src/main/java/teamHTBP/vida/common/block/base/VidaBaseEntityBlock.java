package teamHTBP.vida.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import teamHTBP.vida.api.common.blockentity.ITickableBlockEntity;

import java.util.function.Supplier;

/**
 * @author DustW
 */
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
}
