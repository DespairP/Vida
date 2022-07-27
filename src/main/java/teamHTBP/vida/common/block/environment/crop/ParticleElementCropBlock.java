package teamHTBP.vida.common.block.environment.crop;

import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.api.core.element.IElement;
import teamHTBP.vida.helper.color.ARGBColor;
import teamHTBP.vida.helper.color.VidaColor;

import java.util.Random;

/**
 * 会产生粒子的植物
 */
public class ParticleElementCropBlock extends AbstractElementCropsBlock {
    /**种子*/
    private final ItemLike provider;
    /***/
    private final ARGBColor color;
    protected boolean isMutations = false;


    /**
     * 一个可以在成年时生成粒子的植物
     *
     * @param stage    最大生长数
     * @param element  所属元素
     * @param provider 所提供的itemseed
     * @param color   粒子颜色，使用ColorHelper来表现rgb
     */
    public ParticleElementCropBlock(int stage, IElement element, ItemLike provider, VidaColor color) {
        super(stage, element);
        this.provider = provider;
        this.color = color.toARGB();
    }

    /**
     * 一个*可变异*|*不变异时的植物成年时生成粒子*的植物
     *
     * @param stage       最大生长数
     * @param element     所属元素
     * @param provider    所提供的itemseed
     * @param color      粒子颜色，使用ColorHelper来表现rgb
     * @param isMutations 是否可以变异
     */
    public ParticleElementCropBlock(int stage, IElement element, ItemLike provider, VidaColor color, boolean isMutations) {
        super(stage, element);
        this.provider = provider;
        this.color = color.toARGB();
        this.isMutations = isMutations;
    }


    @Override
    protected ItemLike getSeedsItem() {
        return provider != null ? provider : super.getSeedsItem();
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
        if (isMaxAge(stateIn) && rand.nextFloat() >= 0.75f && color != null) {
            double offsetX = RANDOM.nextDouble() * 0.5D + 0.2D;
            double offsetZ = RANDOM.nextDouble() * 0.5D + 0.2D;
            //worldIn.addParticle(new CuboidParticleOptions(0,0.02D,0,128 - offsetColor,214 - offsetColor, 142 - offsetColor, 0.5f, 100), x + offsetX, y , z + offsetZ,0,0,0);
            worldIn.addParticle(new DustParticleOptions(new Vector3f(
                    color.getR() * 1.0F / 255.0F,
                    color.getG() * 1.0F / 255.0F,
                    color.getB() * 1.0F / 255.0F), 1), x + offsetX, y + 0.4f, z + offsetZ, 0, 0, 0);
        }
    }
}
