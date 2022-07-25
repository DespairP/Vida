package teamHTBP.vida.block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.block.base.BlockMutiDoubleWithY;

import java.util.Random;

public class BlockSteleLife extends BlockMutiDoubleWithY {

    public BlockSteleLife() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE).notSolid());
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);
    }


}
