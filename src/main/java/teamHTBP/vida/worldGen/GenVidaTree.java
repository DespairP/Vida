package teamHTBP.vida.worldGen;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.*;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class GenVidaTree extends AbstractTreeFeature<TreeFeatureConfig> {
    public GenVidaTree() {
        super(TreeFeatureConfig::func_227338_a_);

    }

    protected boolean hasRoom(){
        return true;
    }


    @Override
    protected boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn, TreeFeatureConfig configIn) {
        int height = rand.nextInt(6) + 5;
        if(!this.hasRoom())
        {
            return false;
        }else{
            System.out.println("生成！");
            this.func_227219_b_(generationReader, rand, new BlockPos(positionIn.getX(), positionIn.getY(), positionIn.getZ()), p_225557_5_, boundingBoxIn, configIn);
            return true;
        }
    }
}
