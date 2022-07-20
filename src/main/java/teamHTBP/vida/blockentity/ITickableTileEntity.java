package teamHTBP.vida.blockentity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

/**
 * @author DustW
 */
public interface ITickableTileEntity {
    void tick();

    static <T extends BlockEntity> BlockEntityTicker<T> getTicker() {
        return (pLevel, pPos, pState, pBlockEntity) -> ((ITickableTileEntity) pBlockEntity).tick();
    }
}
