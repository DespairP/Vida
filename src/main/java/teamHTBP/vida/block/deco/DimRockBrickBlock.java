package teamHTBP.vida.block.deco;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class DimRockBrickBlock extends Block implements IDecoBlock {
    private final long tick = 0;

    public DimRockBrickBlock() {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f)
                .sound(SoundType.STONE));
    }

/*
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        this.tick ++ ;

        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
        //System.out.println(tick);
        if(this.tick % 2L == 0L){
            float angleOffset = (float)this.tick / 100.0f;
            //System.out.println(angleOffset);
            worldIn.addParticle(new LeafParticleData(0,0.02D ,angleOffset), x + 0.5F, y + 1.8f, z + 0.5F,0,0.05D ,0);

        }
    }
*/

}
