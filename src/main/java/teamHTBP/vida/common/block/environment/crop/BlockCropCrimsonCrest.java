package teamHTBP.vida.common.block.environment.crop;

import net.minecraft.block.BlockState;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.api.core.element.IElement;
import teamHTBP.vida.common.item.ItemLoader;

import java.util.Random;

@Deprecated
public class BlockCropCrimsonCrest extends AbstractElementCropsBlock {
    public BlockCropCrimsonCrest(int stage, IElement element) {
        super(stage, element);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemLoader.CROP_CRISMCREST.get();
    }

    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
        int offsetColor = rand.nextInt(71);
        if (isMaxAge(stateIn) && rand.nextFloat() >= 0.5f) {
            double offsetX = RANDOM.nextDouble() * 0.5D + 0.2D;
            double offsetZ = RANDOM.nextDouble() * 0.5D + 0.2D;
            //worldIn.addParticle(new CuboidParticleData(0,0.02D,0,255 - offsetColor,71 - offsetColor, 71 - offsetColor, 0.5f, 100), x + offsetX, y , z + offsetZ,0,0,0);
            worldIn.addParticle(new RedstoneParticleData(255.0F / 255.0F, 71.0F / 255.0F, 71.0F / 255.0F, 1), x + offsetX, y + 0.4f, z + offsetZ, 0, 0, 0);

        }
    }
}
