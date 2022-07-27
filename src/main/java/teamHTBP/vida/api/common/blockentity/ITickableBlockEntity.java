package teamHTBP.vida.api.common.blockentity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

/**
 * @author DustW
 */
public interface ITickableBlockEntity {
    void tick();

    static <T extends BlockEntity> BlockEntityTicker<T> getTicker() {
        return (pLevel, pPos, pState, pBlockEntity) -> ((ITickableBlockEntity) pBlockEntity).tick();
    }
}
