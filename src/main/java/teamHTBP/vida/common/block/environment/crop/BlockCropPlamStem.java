package teamHTBP.vida.common.block.environment.crop;

import net.minecraft.block.BlockState;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.core.element.IElement;
import teamHTBP.vida.common.item.ItemLoader;

import java.util.Random;

@Deprecated
public class BlockCropPlamStem extends AbstractElementCropsBlock {
    public BlockCropPlamStem(int stage, IElement element) {
        super(stage, element);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemLoader.CROP_PLAMSTEM.get();
    }

    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
        int offsetColor = rand.nextInt(71);
        if (isMaxAge(stateIn) && rand.nextFloat() >= 0.75f) {
            double offsetX = RANDOM.nextDouble() * 0.5D + 0.2D;
            double offsetZ = RANDOM.nextDouble() * 0.5D + 0.2D;
            //worldIn.addParticle(new CuboidParticleData(0 , 0.02D, 0, 35 , 202 , 249 , 0.5f, 100), x + offsetX, y , z + offsetZ,0,0,0);
            worldIn.addParticle(new RedstoneParticleData(35.0F / 255.0F, 202.0F / 255.0F, 249.0F / 255.0F, 1), x + offsetX, y + 0.4f, z + offsetZ, 0, 0, 0);

        }
    }
}
