package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import teamHTBP.vida.particle.LeafParticleData;

import java.util.Random;

public class BlockDimRockBrick extends Block {
    private long tick = 0;
    public BlockDimRockBrick() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));
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
