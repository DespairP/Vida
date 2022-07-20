package teamHTBP.vida.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.base.ModBaseBlockEntity;

import java.util.List;

public class TileEntityElementCrystal extends ModBaseBlockEntity {
    public float sinWave = 0;
    private int element = 0;

    public TileEntityElementCrystal(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState, int element) {
        super(TileEntityLoader.TileEntityCrystalGold.get(), pWorldPosition, pBlockState);
        this.element = element;
    }

    @Override
    public void tick() {
        if (level.isClientSide)
            if (sinWave > 2 * Math.PI) sinWave = 0;
            else sinWave += 0.1f;
    }

    @Override
    public List<ItemStack> getDrops() {
        return List.of();
    }
}
