package teamHTBP.vida.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import teamHTBP.vida.blockentity.ITickableTileEntity;

/**
 * @author DustW
 */
public class ModBaseEntityBlock<I extends BlockEntity> extends Block implements EntityBlock {
    RegistryObject<BlockEntityType<I>> object;

    public ModBaseEntityBlock(Properties p_49795_, RegistryObject<BlockEntityType<I>> object) {
        super(p_49795_);
        this.object = object;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return object.get().create(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == object.get() ? ITickableTileEntity.getTicker() : null;
    }
}
