package teamHTBP.vida.block.environment.crop;

import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.helper.elementHelper.IElement;
import teamHTBP.vida.item.ItemLoader;

import java.util.Random;

@Deprecated
public class BlockCropHeartOfWal extends AbstractBlockElementCrops {
    public BlockCropHeartOfWal(int stage, IElement element) {
        super(stage, element);
    }

    @Override
    protected ItemLike getSeedsItem() {
        return ItemLoader.CROP_HEARTOFWAL.get();
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
        int offsetColor = rand.nextInt(71);
        if (isMaxAge(stateIn) && rand.nextFloat() >= 0.75f) {
            double offsetX = RANDOM.nextDouble() * 0.5D + 0.2D;
            double offsetZ = RANDOM.nextDouble() * 0.5D + 0.2D;
            //worldIn.addParticle(new CuboidParticleData(0,0.02D,0,128 - offsetColor,214 - offsetColor, 142 - offsetColor, 0.5f, 100), x + offsetX, y , z + offsetZ,0,0,0);
            worldIn.addParticle(new DustParticleOptions(new Vector3f(255.0F / 255.0F, 200.0F / 255.0F, 145.0F / 255.0F), 1), x + offsetX, y + 0.4f, z + offsetZ, 0, 0, 0);

        }
    }
}
