package teamHTBP.vida.block.environment.crop;

import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import teamHTBP.vida.helper.elementHelper.IElement;
import teamHTBP.vida.item.ItemLoader;

import java.util.Random;

@Deprecated
public class BlockCropCrimsonCrest extends AbstractBlockElementCrops {
    public BlockCropCrimsonCrest(int stage, IElement element) {
        super(stage, element);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemLoader.CROP_CRISMCREST.get();
    }

    static final Vector3f COLOR = new Vector3f(Vec3.fromRGB24(0xe14747));

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
        int offsetColor = rand.nextInt(71);
        if (isMaxAge(stateIn) && rand.nextFloat() >= 0.5f) {
            double offsetX = RANDOM.nextDouble() * 0.5D + 0.2D;
            double offsetZ = RANDOM.nextDouble() * 0.5D + 0.2D;
            //worldIn.addParticle(new CuboidParticleData(0,0.02D,0,255 - offsetColor,71 - offsetColor, 71 - offsetColor, 0.5f, 100), x + offsetX, y , z + offsetZ,0,0,0);
            worldIn.addParticle(new DustParticleOptions(COLOR, 1),
                    x + offsetX, y + 0.4f, z + offsetZ,
                    0, 0, 0);

        }
    }
}
