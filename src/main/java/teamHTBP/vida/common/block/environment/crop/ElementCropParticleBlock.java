package teamHTBP.vida.common.block.environment.crop;

import net.minecraft.block.BlockState;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.core.element.IElement;
import teamHTBP.vida.utils.color.ColorHelper;

import java.util.Random;

/**
 * 会产生粒子的植物
 */
public class ElementCropParticleBlock extends AbstractElementCropsBlock {
    /**种子*/
    private final IItemProvider provider;
    /***/
    private final ColorHelper color;
    protected boolean isMutations = false;


    /**
     * 一个可以在成年时生成粒子的植物
     *
     * @param stage    最大生长数
     * @param element  所属元素
     * @param provider 所提供的itemseed
     * @param helper   粒子颜色，使用ColorHelper来表现rgb
     */
    public ElementCropParticleBlock(int stage, IElement element, IItemProvider provider, ColorHelper helper) {
        super(stage, element);
        this.provider = provider;
        this.color = helper;
    }

    /**
     * 一个*可变异*|*不变异时的植物成年时生成粒子*的植物
     *
     * @param stage       最大生长数
     * @param element     所属元素
     * @param provider    所提供的itemseed
     * @param helper      粒子颜色，使用ColorHelper来表现rgb
     * @param isMutations 是否可以变异
     */
    public ElementCropParticleBlock(int stage, IElement element, IItemProvider provider, ColorHelper helper, boolean isMutations) {
        super(stage, element);
        this.provider = provider;
        this.color = helper;
        this.isMutations = isMutations;
    }


    @Override
    protected IItemProvider getSeedsItem() {
        return provider != null ? provider : super.getSeedsItem();
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
        if (isMaxAge(stateIn) && rand.nextFloat() >= 0.75f && color != null) {
            double offsetX = RANDOM.nextDouble() * 0.5D + 0.2D;
            double offsetZ = RANDOM.nextDouble() * 0.5D + 0.2D;
            //worldIn.addParticle(new CuboidParticleData(0,0.02D,0,128 - offsetColor,214 - offsetColor, 142 - offsetColor, 0.5f, 100), x + offsetX, y , z + offsetZ,0,0,0);
            worldIn.addParticle(new RedstoneParticleData(color.r * 1.0F / 255.0F, color.g * 1.0F / 255.0F, color.b * 1.0F / 255.0F, 1), x + offsetX, y + 0.4f, z + offsetZ, 0, 0, 0);
        }
    }
}
